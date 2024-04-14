package com.oys.lolgak.ui.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.oys.lolgak.ui.model.Account


@Composable
fun DetailUserScreen(
    account: Account
) {
    Text(text = account.gameName)
}