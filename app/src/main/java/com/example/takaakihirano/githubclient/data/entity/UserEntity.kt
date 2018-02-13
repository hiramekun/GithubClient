package com.example.takaakihirano.githubclient.data.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by takaakihirano on 2018/02/11.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
open class UserEntity(
        @PrimaryKey
        open var id: Int=0,
        open var login: String="",
        open var avatar_url: String="",
        open var name: String="",
        open var url: String=""
) : RealmObject()
