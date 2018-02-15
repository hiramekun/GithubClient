package com.example.takaakihirano.githubclient.data.net

import com.example.takaakihirano.githubclient.data.repository.AuthInfoRepo
import com.fasterxml.jackson.databind.JsonNode
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.POST

/**
 * Created by takaakihirano on 2018/02/13.
 */

object AuthAPIRepo : APIClient() {
    fun requestAuth(clientId: String,
                    clientSecret: String,
                    note: String,
                    scopes: List<String>): Single<JsonNode> {
        return createService(AuthApiService::class.java, AuthInfoRepo.getAccessToken()).requestAuth(clientId, clientSecret, note, scopes)
    }
}

private interface AuthApiService {
    @POST("authorizations")
    fun requestAuth(@Field("client_id") clientId: String,
                    @Field("client_secret") clientSecret: String,
                    @Field("note") note: String,
                    @Field("scopes") scopes: List<String>): Single<JsonNode>
}