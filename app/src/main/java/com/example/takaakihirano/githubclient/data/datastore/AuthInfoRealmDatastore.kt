package com.example.takaakihirano.githubclient.data.datastore

import com.example.takaakihirano.githubclient.data.entity.AuthInfoEntity
import com.example.takaakihirano.githubclient.presentation.model.AuthInfo
import com.example.takaakihirano.githubclient.service.RealmService
import io.reactivex.Completable
import io.realm.Realm

/**
 * Created by takaakihirano on 2018/02/22.
 */

object AuthInfoRealmDatastore {
    fun saveToken(token: String): Completable {
        return RealmService.executeTransactionAsync(Realm.Transaction {
            it.where(AuthInfoEntity::class.java).findFirst()!!.accessToken = token
        })
    }

    fun save(authInfo: AuthInfo): Completable {
        return RealmService.executeTransactionAsync(Realm.Transaction {
            val authInfoEntity = AuthInfoEntity(githubId = authInfo.githubId, githubSecret = authInfo.githubSecret, accessToken = authInfo.accessToken)
            it.copyToRealmOrUpdate(authInfoEntity)
        })
    }
}