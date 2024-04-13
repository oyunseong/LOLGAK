package com.oys.lolgak.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oys.lolgak.data.repository.RiotGamesRepository
import com.oys.lolgak.extensions.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RiotGamesRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun inputName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun getAccountByRiotId(gameName: String, tagLine: String, apiKey: String) =
        viewModelScope.launch {
            val result = repository.getAccountByRiotId(gameName, tagLine, apiKey)
            "result : $result".log()
        }
}