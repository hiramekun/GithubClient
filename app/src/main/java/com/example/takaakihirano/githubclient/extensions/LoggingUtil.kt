package com.example.takaakihirano.githubclient.extensions

import android.util.Log
import com.example.takaakihirano.githubclient.BuildConfig

/**
 * Created by takaakihirano on 2018/02/11.
 */

fun String.log_d(tag: String = "") {
    if (BuildConfig.DEBUG) {
        Log.d(tag, this)
    }
}

fun Int.log_d(tag: String = "") {
    if (BuildConfig.DEBUG) {
        Log.d(tag, toString())
    }
}

fun errorIfDebug() {
    if (BuildConfig.DEBUG) {
        error("")
    }
}