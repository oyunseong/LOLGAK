package com.oys.lolgak.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oys.lolgak.data.repository.RiotGamesRepository
import com.oys.lolgak.extensions.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailUserScreenViewModel @Inject constructor(
    private val repository: RiotGamesRepository
) : ViewModel() {

    fun getPlayingGameBySummoner(encryptedPUUID: String, apiKey: String) {
        viewModelScope.launch {
            val result = repository.getActiveGameBySummoner(encryptedPUUID, apiKey)
            "encryptedPUUID : $encryptedPUUID".log()
            result.onSuccess {
                "경기 : $it".log()
            }.onFailure {
                "실패".log()
                it.printStackTrace()
            }
        }

    }
}