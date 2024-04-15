package com.oys.lolgak.ui.spell

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oys.lolgak.ui.spell.model.SpellUiState
import com.oys.lolgak.ui.spell.model.SummonerSpell
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SpellScreenViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState: MutableStateFlow<SpellUiState> = MutableStateFlow(SpellUiState())
    val uiState: StateFlow<SpellUiState> = _uiState

//    private val _spellTimers = MutableStateFlow(List(10) { 0 })
//    val spellTimers: StateFlow<List<Int>> = _spellTimers.asStateFlow()

//    private var countDownJobs: List<Job?> = emptyList()


    private var spellJob: Job? = null
    private val spellJobs = mutableListOf<Job?>()
    init {
        viewModelScope.launch {
            _uiState.emit(
                uiState.value.copy(
                    summoner = listOf(
                        SummonerSpell("아트록스", "점화", "점멸", 180, 300, false),
                        SummonerSpell("베인", "점화", "점멸", 180, 300, false),
                        SummonerSpell("아트록스", "점화", "점멸", 180, 300, false),
                    )
                )
            )
            spellJobs.clear()
            spellJobs.addAll(List(uiState.value.summoner.size) { null })
        }
    }


    val timer: MutableStateFlow<List<Int>> = MutableStateFlow(List(10) { 1 })
    fun startSpell(index: Int, time: Int) {
        spellJobs.getOrNull(index)?.cancel() // 기존 잡이 있으면 취소
        spellJobs[index] = viewModelScope.launch {
            var currentTime = time
            _uiState.emit(
                uiState.value.copy(
                    summoner = uiState.value.summoner.toMutableList().apply {
                        this[index] = uiState.value.summoner[index].copy(DTime = currentTime)
                    })
            )
            while (currentTime > 0) {
                delay(1000)
                currentTime--
                _uiState.emit(
                    uiState.value.copy(
                        summoner = uiState.value.summoner.toMutableList().apply {
                            this[index] = uiState.value.summoner[index].copy(DTime = currentTime)
                        })
                )
            }
        }
    }

    fun decreaseSpellTime(index: Int) {
        val currentTime = uiState.value.summoner[index].DTime
        if (currentTime > 0) {
            startSpell(index, currentTime - 1) // 현재 타이머에서 1을 뺀 값으로 startSpell 호출
        }
    }

//    fun startSpell(index: Int, time: Int) {
//        countDownJobs[index]?.cancel()
//        val job = countDownJobs.toMutableList()
//        job[index] = viewModelScope.launch {
//            timer[index].emit(time)
//            while (timer.value > 0) {
//                delay(1000)
//                timer.emit(timer.value - 1)
//            }
//        }
//    }

//    fun startSpell(index: Int, time: Int) {
//        spellJob?.cancel() // 기존 잡이 있으면 취소
//        spellJob = viewModelScope.launch {
//            timer.emit(time)
//            while (timer.value > 0) {
//                delay(1000)
//                timer.emit(timer.value - 1)
//            }
//        }
//    }

//    fun decreaseSpellTime() {
//        val currentTime = timer.value
//        if (currentTime > 0) {
//            startSpell(currentTime - 1) // 현재 타이머에서 1을 뺀 값으로 startSpell 호출
//        }
//    }

}