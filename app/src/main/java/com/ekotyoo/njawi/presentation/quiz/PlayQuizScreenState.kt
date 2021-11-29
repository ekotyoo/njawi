package com.ekotyoo.njawi.presentation.quiz

import com.ekotyoo.njawi.common.Constants
import com.ekotyoo.njawi.domain.models.Quiz

data class PlayQuizScreenState(
    val isLoading: Boolean = false,
    val quiz: Quiz = Constants.getQuiz(),
    val error: String = ""
)
