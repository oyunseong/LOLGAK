package com.oys.lolgak.data.remote.model

data class ParticipantResponse(
    val puuid: String,
    val teamId: Long,
    val spell1Id: Long,
    val spell2Id: Long,
    val championId: Long,
    val profileIconId: Long,
    val riotId: String,
    val bot: Boolean,
    val summonerId: String,
    val gameCustomizationObjects: List<Any>,
    val perks: PerksResponse
)
