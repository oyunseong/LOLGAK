package com.oys.lolgak.data.repository

import com.oys.lolgak.data.remote.di.RiotGamesApi
import com.oys.lolgak.data.remote.model.AccountDtoResponse
import javax.inject.Inject

class RiotGamesRepository @Inject constructor(
    private val api: RiotGamesApi
) {
    suspend fun getAccountByRiotId(gameName: String, tagLine: String, apiKey: String): Result<AccountDtoResponse> {
        return try {
            val response = api.getAccountByRiotId(gameName, tagLine, apiKey)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("API request failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}