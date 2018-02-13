package com.example.takaakihirano.githubclient.service

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
}
