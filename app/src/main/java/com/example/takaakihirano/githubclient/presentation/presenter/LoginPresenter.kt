package com.example.takaakihirano.githubclient.presentation.presenter

import com.example.takaakihirano.githubclient.data.entity.UserEntity
import com.example.takaakihirano.githubclient.presentation.view.LoginView
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

/**
 * Created by takaakihirano on 2018/02/12.
 */
class LoginPresenter : Presenter {

    private val inRequestUser = PublishSubject.create<UserEntity>()!!
//    private val outUserEntity = inRequestUser.flatMapSingle {
//    }

    fun initialize(view: LoginView) {
        view.renderView()
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
    }
}
