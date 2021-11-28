package com.ekotyoo.njawi.domain.repository

import com.ekotyoo.njawi.domain.models.Quiz
import com.ekotyoo.njawi.domain.models.Response
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun getQuizzesFromFirestore(): Flow<Response<List<Quiz>>>
}