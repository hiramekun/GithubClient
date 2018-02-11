package com.example.takaakihirano.githubclient.data

import com.example.takaakihirano.githubclient.BuildConfig
import com.example.takaakihirano.githubclient.extensions.errorIfDebug
import com.fasterxml.jackson.databind.ObjectMapper
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.lang.reflect.Type

/**
 * Created by takaakihirano on 2017/08/11.
 */

private const val SERVER_ENDPOINT = "https://github.com"

abstract class APIClient {
    protected companion object {
        fun <T> createService(service: Class<T>, accessToken: String?) =
                Retrofit.Builder()
                        .client(createClient(accessToken))
                        .baseUrl(SERVER_ENDPOINT)
                        .addConverterFactory(JacksonConverterFactory.create(ObjectMapper()))
                        .addCallAdapterFactory(RxThreadingCallAdapterFactory.create())
                        .build()
                        .create(service)!!

        private fun createClient(accessToken: String?): OkHttpClient =
                OkHttpClient.Builder()
                        .also { builder ->
                            // add header.
                            if (accessToken != null) {
                                builder.addInterceptor { chain ->
                                    chain.proceed(
                                            chain.request().newBuilder()
                                                    .addHeader("Authorization", accessToken)
                                                    .build()
                                    )
                                }
                            }

                            // enable logging.
                            if (BuildConfig.DEBUG) {
                                builder.addInterceptor(HttpLoggingInterceptor().also {
                                    it.level = HttpLoggingInterceptor.Level.BODY
                                })
                            }
                        }
                        .build()
    }
}

/**
 * RxJava adapter for Retrofit.
 * Always `subscribeOn(Schedulers.io())`
 *        `observeOn(AndroidSchedulers.mainThread())`
 */
private class RxThreadingCallAdapterFactory : CallAdapter.Factory() {

    companion object {
        fun create(): CallAdapter.Factory = RxThreadingCallAdapterFactory()
    }

    // always subscribe on a new thread.
    private val original: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *> {
        // `original.get() == null` -> Retrofit throws IllegalArgumentException.
        return RxCallAdapterWrapper(original.get(returnType, annotations, retrofit)!!)
    }

    private class RxCallAdapterWrapper<R>(private val wrapped: CallAdapter<R, *>) : CallAdapter<R, Any> {
        override fun responseType(): Type = wrapped.responseType()

        // always observe on MainThread.
        override fun adapt(call: Call<R>) = wrapped.adapt(call)
                .let {
                    when (it) {
                        is Completable -> {
                            it.observeOn(AndroidSchedulers.mainThread())
                        }
                        is Observable<*> -> {
                            it.observeOn(AndroidSchedulers.mainThread())
                        }
                        is Single<*> -> {
                            it.observeOn(AndroidSchedulers.mainThread())
                        }
                        is Flowable<*> -> {
                            it.observeOn(AndroidSchedulers.mainThread())
                        }
                        is Maybe<*> -> {
                            it.observeOn(AndroidSchedulers.mainThread())
                        }
                        else -> errorIfDebug()
                    }
                }!!
    }
}

