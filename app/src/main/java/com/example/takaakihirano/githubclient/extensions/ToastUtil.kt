package com.example.takaakihirano.githubclient.extensions

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

/**
 * Created by takaakihirano on 2018/02/22.
 */

fun Context.toast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Context.toast(@StringRes resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
}

