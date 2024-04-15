package com.oys.lolgak.ui.spell.model

data class SummonerSpell(
    val name: String,   // 챔프 이름
    val spellDName: String,
    val spellFName: String,
    val DTime: Int,     // D 스킬 쿨타임
    val FTime: Int,      // F 스킬 쿨타임
    val isCosmicInsight: Boolean
)