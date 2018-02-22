package com.example.takaakihirano.githubclient.service

import io.reactivex.Completable
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by takaakihirano on 2018/02/11.
 */

object RealmService {
    fun getInstanceForDevelopment(): Realm =
            Realm.getInstance(RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build())

    fun <E> query(query: (Realm) -> E): E? {
        var ret: E? = null
        getInstanceForDevelopment().also {
            ret = query(it)
            it.close()
        }

        return ret
    }

    fun executeTransactionAsync(transaction: Realm.Transaction): Completable {
        return Completable.create { e ->
            val realm = getInstanceForDevelopment()
            realm.executeTransactionAsync(transaction,
                    Realm.Transaction.OnSuccess {
                        realm.close()
                        e.onComplete()
                    })
        }
    }

}
