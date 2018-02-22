package com.example.takaakihirano.githubclient.data.datastore

import com.example.takaakihirano.githubclient.data.entity.AuthInfoEntity
import com.example.takaakihirano.githubclient.presentation.model.AuthInfo
import com.example.takaakihirano.githubclient.service.RealmService
import com.fasterxml.jackson.databind.JsonNode
import io.reactivex.Completable
import io.realm.Realm

/**
 * Created by takaakihirano on 2018/02/22.
 */

object AuthInfoRealmDatastore {
    fun save(jsonNode: JsonNode): Completable {
        TODO()
    }

    fun save(authInfo: AuthInfo): Completable {
        return RealmService.executeTransactionAsync(Realm.Transaction {
            val authInfoEntity = AuthInfoEntity(githubId = authInfo.githubId, githubSecret = authInfo.githubSecret, accessToken = authInfo.accessToken)
            it.copyToRealmOrUpdate(authInfoEntity)
        })
    }
}