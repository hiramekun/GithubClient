package com.example.takaakihirano.githubclient.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.takaakihirano.githubclient.R
import com.example.takaakihirano.githubclient.data.repository.SharedPreferenceRepo
import com.example.takaakihirano.githubclient.presentation.navigation.Navigator

class MainActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (SharedPreferenceRepo.getAccessToken(this).isEmpty()) {
            Navigator.navigateToLogin(this)
            finish()
        }
        setContentView(R.layout.activity_main)
    }
}
