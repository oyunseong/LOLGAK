package com.oys.lolgak.data.remote.model

data class BannedChampionResponse(
    val championId: Long,
    val teamId: Long,
    val pickTurn: Long
)