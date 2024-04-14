package com.oys.lolgak.data.remote.di

import com.oys.lolgak.data.remote.model.CurrentGameInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RiotGamesSpectatorApi {
    @GET("lol/spectator/v5/active-games/by-summoner/{encryptedPUUID}")
    suspend fun getActiveGameBySummoner(
        @Path("encryptedPUUID") encryptedPUUID: String,
        @Query("api_key") apiKey: String
    ): Response<CurrentGameInfoResponse>
}