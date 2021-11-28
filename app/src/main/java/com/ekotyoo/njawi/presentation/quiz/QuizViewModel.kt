package com.ekotyoo.njawi.presentation.quiz

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekotyoo.njawi.domain.models.Quiz
import com.ekotyoo.njawi.domain.models.Response
import com.ekotyoo.njawi.domain.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@InternalCoroutinesApi
class QuizViewModel (
    private val repository: QuizRepository
): ViewModel() {
    private val _quizzesState = mutableStateOf<Response<List<Quiz>>>(Response.Loading)
    val quizzesState: State<Response<List<Quiz>>> = _quizzesState

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
}