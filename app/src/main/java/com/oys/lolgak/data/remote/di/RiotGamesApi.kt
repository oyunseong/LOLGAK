package com.oys.lolgak.data.remote.di

import com.oys.lolgak.data.remote.model.AccountDtoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RiotGamesApi {
    @GET("account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
    suspend fun getAccountByRiotId(
        @Path("gameName") gameName: String,
        @Path("tagLine") tagLine: String,
        @Query("api_key") apiKey: String
    ): Response<AccountDtoResponse>
}