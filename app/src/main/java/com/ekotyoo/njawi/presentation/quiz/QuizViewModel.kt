package com.ekotyoo.njawi.presentation.quiz

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekotyoo.njawi.domain.models.Leaderboard
import com.ekotyoo.njawi.domain.models.Quiz
import com.ekotyoo.njawi.domain.models.Response
import com.ekotyoo.njawi.domain.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class QuizViewModel @Inject constructor (
    private val repository: QuizRepository
): ViewModel() {
    private val _quizzesState = mutableStateOf<Response<List<Quiz>>>(Response.Loading)
    private val _leaderboardsState = mutableStateOf<Response<List<Leaderboard>>>(Response.Loading)

    val quizzesState: State<Response<List<Quiz>>> = _quizzesState
    val leaderboardState: State<Response<List<Leaderboard>>> = _leaderboardsState

    init {
        getQuizzes()
    }

    private fun getQuizzes() {
        viewModelScope.launch {
            repository.getQuizzesFromFirestore().collect { response ->
                _quizzesState.value = response
            }
        }
    }

    fun getLeaderboards() {
        viewModelScope.launch {
            repository.getLeaderboardFromFirestore().collect { response ->
                _leaderboardsState.value = response
            }
        }
    }
}