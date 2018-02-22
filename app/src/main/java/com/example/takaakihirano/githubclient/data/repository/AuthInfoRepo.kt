package com.example.takaakihirano.githubclient.data.repository

import com.example.takaakihirano.githubclient.data.datastore.AuthAPIDatastore
import com.example.takaakihirano.githubclient.data.datastore.AuthInfoRealmDatastore
import com.example.takaakihirano.githubclient.data.entity.AuthInfoEntity
import com.example.takaakihirano.githubclient.presentation.model.AuthInfo
import com.example.takaakihirano.githubclient.service.RealmService
import io.reactivex.Completable

/**
 * Created by takaakihirano on 2018/02/13.
 */

object AuthInfoRepo {

    fun request(clientId: String = getAuthInfo().githubId,
                clientSecret: String = getAuthInfo().githubSecret,
                note: String = "",
                scopes: List<String> = emptyList()): Completable {
        return AuthAPIDatastore.requestAuth(clientId, clientSecret, note, scopes).flatMapCompletable {
            AuthInfoRealmDatastore.save(it)
        }
    }

    /**
     * @return empty string if AuthInfoEntity if null.
     */
    fun getAccessToken(): String {
        return RealmService.query { it.where(AuthInfoEntity::class.java).findFirst()?.accessToken }
                ?: ""
    }

    fun getAuthInfo(): AuthInfo {
        return RealmService.query {
            it.where(AuthInfoEntity::class.java).findFirst()?.let {
                AuthInfo(accessToken = it.accessToken, githubId = it.githubId, githubSecret = it.githubSecret)
            }
        } ?: AuthInfo()
    }
}