package com.example.takaakihirano.githubclient.presentation.view

/**
 * Created by takaakihirano on 2018/02/13.
 */

interface LoginView {
    fun renderView()
    fun showProgress(show: Boolean)
    fun attemptLogin()
}
