package com.oys.lolgak.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oys.lolgak.data.repository.RiotGamesRepository
import com.oys.lolgak.extensions.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val repository: RiotGamesRepository
) : ViewModel() {

    fun getAccountByRiotId(gameName: String, tagLine: String, apiKey: String) =
        viewModelScope.launch {
            val result = repository.getAccountByRiotId(gameName, tagLine, apiKey)
            "result : $result".log()
        }
}