package com.example.takaakihirano.githubclient.data.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by takaakihirano on 2018/02/13.
 */
open class AuthInfoEntity(
        @PrimaryKey val id: Int = 0,
        var githubId: String = "",
        var githubSecret: String = "",
        var accessToken: String = ""
) : RealmObject()