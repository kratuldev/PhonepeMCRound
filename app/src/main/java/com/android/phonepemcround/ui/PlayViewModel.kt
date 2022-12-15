package com.android.phonepemcround.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.phonepemcround.data.model.CompanyInfo
import com.android.phonepemcround.data.repo.CompanyDataRepo
import com.android.phonepemcround.domain.Randomizer
import com.android.phonepemcround.util.toUiDat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayViewModel @Inject constructor(
    private val repo: CompanyDataRepo,
    private val randomizer: Randomizer
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(Loading)

    val uiState: Flow<UiState> = _uiState

    private val data = MutableStateFlow<List<UiData>>(emptyList())

    private var currentPos = 0

    init {
        viewModelScope.launch(Dispatchers.IO) {
            data.collect {
                _uiState.emit(
                    Success(
                        it[currentPos]
                    )
                )
            }
        }
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getData().onStart {
                _uiState.emit(Loading)
            }.catch {
                _uiState.emit(Failure("Something went wrong", 500))
            }.collect { companyInfo ->
                if (companyInfo == null)
                    _uiState.emit(Failure("Empty data", 501))
                else {
                    data.emit(companyInfo.map {
                        it.toUiDat(randomizer.randomize(it.name))
                    })
                }
            }
        }
    }

    fun checkAnswer(answer: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (answer == data.value[currentPos].actualName)
                _uiState.emit(CorrectAnswer)
            else
                _uiState.emit(InCorrectAnswer)
            //delay for showing UI for Correct & Incorrect Answer and automatically moving forward
            delay(2000)
            playNext()
        }
    }

    private suspend fun playNext() {
        if (currentPos < data.value.size) {
            _uiState.emit(Success(data.value[currentPos]))
            currentPos++
        } else {
            _uiState.emit(GameFinished)
        }
    }
}

sealed interface UiState

object Loading : UiState

data class Success(val data: UiData) : UiState

data class Failure(
    val error: String,
    val code: Int
) : UiState

data class UiData(
    val imageSource: String,
    val randomizedString: String,
    val actualName: String
)

object GameFinished : UiState

object CorrectAnswer : UiState

object InCorrectAnswer : UiState
