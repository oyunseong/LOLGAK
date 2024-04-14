package com.oys.lolgak.data.remote.model


data class CurrentGameInfoResponse(
    val gameId: Long,
    val mapId: Long,
    val gameMode: String,
    val gameType: String,
    val gameQueueConfigId: Long,
    val participants: List<ParticipantResponse>,
    val observers: ObserversResponse,
    val platformId: String,
    val bannedChampions: List<BannedChampionResponse>,
    val gameStartTime: Long,
    val gameLength: Long
)
