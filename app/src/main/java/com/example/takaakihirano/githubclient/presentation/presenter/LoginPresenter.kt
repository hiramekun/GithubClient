package com.example.takaakihirano.githubclient.presentation.presenter

import com.example.takaakihirano.githubclient.data.repository.AuthInfoRepo
import com.example.takaakihirano.githubclient.presentation.view.LoginView
import io.reactivex.subjects.PublishSubject

/**
 * Created by takaakihirano on 2018/02/12.
 */
class LoginPresenter : Presenter {

    private val inRequestUser = PublishSubject.create<Boolean>()!!
    val outRequest = inRequestUser.flatMapCompletable { AuthInfoRepo.request() }!!

    fun initialize(view: LoginView) {
        view.renderView()
    }

    fun requestAuth(isRequesting: Boolean) {
        inRequestUser.onNext(isRequesting)
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
    }
}
