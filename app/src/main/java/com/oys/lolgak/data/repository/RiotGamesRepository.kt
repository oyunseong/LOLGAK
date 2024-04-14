package com.oys.lolgak.data.repository

import com.oys.lolgak.data.remote.di.RiotGamesAccountApi
import com.oys.lolgak.data.remote.di.RiotGamesSpectatorApi
import com.oys.lolgak.data.remote.model.AccountDtoResponse
import com.oys.lolgak.data.remote.model.CurrentGameInfoResponse
import javax.inject.Inject

class RiotGamesRepository @Inject constructor(
    private val accountApi: RiotGamesAccountApi,
    private val spectatorApi: RiotGamesSpectatorApi
) {
    suspend fun getAccountByRiotId(
        gameName: String,
        tagLine: String,
        apiKey: String
    ): Result<AccountDtoResponse> {
        return try {
            val response = accountApi.getAccountByRiotId(gameName, tagLine, apiKey)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("API request failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getActiveGameBySummoner(
        encryptedPUUID: String,
        apiKey: String
    ): Result<CurrentGameInfoResponse> {
        return try {
            val response = spectatorApi.getActiveGameBySummoner(encryptedPUUID, apiKey)
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