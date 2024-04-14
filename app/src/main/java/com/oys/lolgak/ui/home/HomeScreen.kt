package com.oys.lolgak.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.gson.Gson
import com.oys.lolgak.Navigation
import com.oys.lolgak.R
import com.oys.lolgak.extensions.log
import com.oys.lolgak.ui.common.CommonTextField
import com.oys.lolgak.ui.common.VerticalSpacer

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigate: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val gson = Gson()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.onSuccessSearchEvent.collect {
            val accountJson = gson.toJson(it)
            accountJson.log()
            navigate.invoke("${Navigation.Routes.DetailUserScreen}/$accountJson")
        }
    })

    HomeScreenContent(
        input = uiState.name,
        tag = uiState.tag,
        uiEvent = {
            when (it) {
                is HomeEvent.InputUserName -> {
                    viewModel.inputUserName(it.name)
                }

                is HomeEvent.InputTag -> {
                    viewModel.inputTag(it.tag)
                }

                HomeEvent.OnSearchEvent -> {
                    viewModel.getAccountByRiotId(
                        gameName = uiState.name,
                        tagLine = uiState.tag,
                        apiKey = context.getString(R.string.riot_api_key)
                    )
                }


            }

        }
    )
}

sealed interface HomeEvent {
    data class InputUserName(val name: String) : HomeEvent
    data class InputTag(val tag: String) : HomeEvent
    object OnSearchEvent : HomeEvent

}

@Composable
fun HomeScreenContent(
    input: String = "",
    tag: String = "",
    uiEvent: (HomeEvent) -> Unit = {},
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        CommonTextField(
            modifier = Modifier.fillMaxWidth(),
            value = input,
            hintText = "닉네임 입력",
            onValueChange = {
                uiEvent.invoke(HomeEvent.InputUserName(it))
            },
        )
        VerticalSpacer(dp = 4.dp)

        CommonTextField(
            modifier = Modifier.fillMaxWidth(),
            value = tag,
            hintText = "태그 입력(선택)",
            onValueChange = {
                uiEvent.invoke(HomeEvent.InputTag(it))
            },
        )
        Button(onClick = { uiEvent.invoke(HomeEvent.OnSearchEvent) }) {
            Text(text = "검색")
        }
    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreenContent()
}