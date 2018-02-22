package com.example.takaakihirano.githubclient.presentation.model

/**
 * Created by takaakihirano on 2018/02/13.
 */
data class User(
        var id: Int = 0,
        var login: String = "",
        var avatar_url: String = "",
        var name: String = "",
        var url: String = ""
)
