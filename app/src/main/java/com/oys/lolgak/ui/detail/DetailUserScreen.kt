package com.oys.lolgak.ui.detail

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.oys.lolgak.R
import com.oys.lolgak.ui.model.Account


@Composable
fun DetailUserScreen(
    account: Account,
    viewModel: DetailUserScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    Text(text = account.gameName)

    Button(onClick = {
        viewModel.getPlayingGameBySummoner(
            encryptedPUUID = account.puuid,
            apiKey = context.getString(R.string.riot_api_key)
        )
    }) {
        Text(text = " 현재 게임 정보 가져오기")
    }
}