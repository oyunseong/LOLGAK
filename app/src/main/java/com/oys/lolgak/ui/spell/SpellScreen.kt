package com.oys.lolgak.ui.spell

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.oys.lolgak.ui.spell.model.SummonerSpell

@Composable
fun SpellScreen(
    viewModel: SpellScreenViewModel = hiltViewModel()
) {
//    val spellTimers = viewModel.spellTimers.collectAsState()
    val timer = viewModel.timer.collectAsState()
    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SummonerSpellColumn(
            summonerSpell = uiState.value.summoner[0],
            startSpell = {
                viewModel.startSpell(0, 300)
            },
            minusTime = {
                viewModel.decreaseSpellTime(0)
            }
        )
        SummonerSpellColumn(
            summonerSpell = uiState.value.summoner[1],
            startSpell = {
                viewModel.startSpell(1, 200)
            },
            minusTime = {
                viewModel.decreaseSpellTime(1)
            }
        )
    }
}

@Composable
fun SummonerSpellColumn(
    summonerSpell: SummonerSpell,
    startSpell: () -> Unit,
    minusTime: () -> Unit,
) {
    Column {
        Text(
            text = "summonerSpell : ${summonerSpell.name}/ ${summonerSpell.DTime} seconds",
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                startSpell.invoke()
            }
        ) {
            Text("Start Spell 1")
        }
        Button(
            onClick = {
                minusTime.invoke()
            }
        ) {
            Text("minus 1")
        }
    }
}