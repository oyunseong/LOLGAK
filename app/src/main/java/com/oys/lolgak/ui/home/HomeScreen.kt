package com.oys.lolgak.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.oys.lolgak.R

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getAccountByRiotId(
            gameName = "오윤성",
            tagLine = "kr1",
            apiKey = context.getString(R.string.riot_api_key)
        )
    })

    HomeScreenContent()
}

@Composable
fun HomeScreenContent() {
    Column {

    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreenContent()
}