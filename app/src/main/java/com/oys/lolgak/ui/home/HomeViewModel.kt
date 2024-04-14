package com.oys.lolgak.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oys.lolgak.data.repository.RiotGamesRepository
import com.oys.lolgak.extensions.log
import com.oys.lolgak.ui.model.Account
import com.oys.lolgak.ui.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
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

    val onSuccessSearchEvent: MutableSharedFlow<Account> = MutableSharedFlow()


    fun inputUserName(name: String) {
        viewModelScope.launch {
            name.log()
            _uiState.emit(uiState.value.copy(name = name))
        }
    }

    fun inputTag(tag: String) {
        viewModelScope.launch {
            tag.log()
            _uiState.emit(uiState.value.copy(tag = tag))
        }
    }

    fun getAccountByRiotId(
        gameName: String, tagLine: String, apiKey: String,
    ) {
        viewModelScope.launch {
            try {
                val result = repository.getAccountByRiotId(gameName, tagLine, apiKey)
                result.onSuccess {
                    onSuccessSearchEvent.emit(it.toUiModel())
                }.onFailure {
                    it.printStackTrace()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}