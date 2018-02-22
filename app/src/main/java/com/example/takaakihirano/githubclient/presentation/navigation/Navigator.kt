package com.example.takaakihirano.githubclient.presentation.navigation

import android.content.Context
import com.example.takaakihirano.githubclient.presentation.view.LoginActivity
import com.example.takaakihirano.githubclient.presentation.view.MainActivity

/**
 * Created by takaakihirano on 2018/02/13.
 */
object Navigator {
    fun navigateToLogin(context: Context) {
        context.startActivity(LoginActivity.createIntent(context))
    }

    fun navigateToMain(context: Context) {
        context.startActivity(MainActivity.createIntent(context))
    }
}