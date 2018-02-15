package com.example.takaakihirano.githubclient.data.repository

import com.example.takaakihirano.githubclient.data.entity.AuthInfo
import com.example.takaakihirano.githubclient.service.RealmService

/**
 * Created by takaakihirano on 2018/02/13.
 */

object AuthInfoRepo {
    /**
     * @return empty string if AuthInfo if null.
     */
    fun getAccessToken(): String {
        return RealmService.query { it.where(AuthInfo::class.java).findFirst()?.accessToken } ?: ""
    }
}