package com.example.takaakihirano.githubclient.data.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by takaakihirano on 2018/02/11.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
open class EventEntity(
        @PrimaryKey
        open var id: Int = 0,
        open var created_at: Date = Date(),
        open var actor: UserEntity? = null
) : RealmObject()
