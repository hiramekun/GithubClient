package com.example.takaakihirano.githubclient.application

import android.app.Application
import com.example.takaakihirano.githubclient.R
import com.example.takaakihirano.githubclient.data.datastore.AuthInfoRealmDatastore
import com.example.takaakihirano.githubclient.presentation.model.AuthInfo
import io.realm.Realm

/**
 * Created by takaakihirano on 2018/02/22.
 */

class GithubClientApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // init realm
        Realm.init(this)

        // saveToken your github info
        AuthInfoRealmDatastore.save(
                AuthInfo(githubId = getString(R.string.github_id),
                        githubSecret = getString(R.string.github_secret))
        )
    }
}