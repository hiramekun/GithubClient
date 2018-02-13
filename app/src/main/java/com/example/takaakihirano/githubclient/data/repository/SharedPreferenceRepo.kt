package com.example.takaakihirano.githubclient.data.repository

import android.content.Context
import android.preference.PreferenceManager

/**
 * Created by takaakihirano on 2018/02/13.
 */

private const val ACCESS_TOKEN = "access_token"

object SharedPreferenceRepo {

    fun saveAccessToken(context: Context, accessToken: String) {
        val edit = PreferenceManager.getDefaultSharedPreferences(context).edit()
        edit.putString(ACCESS_TOKEN, accessToken)
        edit.apply()
    }

    fun getAccessToken(context: Context) = PreferenceManager.getDefaultSharedPreferences(context).getString(ACCESS_TOKEN, "")
}