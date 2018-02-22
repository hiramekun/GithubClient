package com.example.takaakihirano.githubclient.presentation.model

/**
 * Created by takaakihirano on 2018/02/22.
 */

data class AuthInfo(
        val id: Int = 0,
        var githubId: String = "",
        var githubSecret: String = "",
        var accessToken: String = ""
)
