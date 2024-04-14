package com.oys.lolgak.ui.model

import com.oys.lolgak.data.remote.model.AccountDtoResponse

data class Account(
    val puuid: String,
    val gameName: String,
    val tagLine: String,
)

fun AccountDtoResponse.toUiModel(): Account {
    return Account(
        puuid = puuid,
        gameName = gameName,
        tagLine = tagLine,
    )
}