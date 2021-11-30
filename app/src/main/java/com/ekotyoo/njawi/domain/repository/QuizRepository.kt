package com.ekotyoo.njawi.domain.repository

import com.ekotyoo.njawi.domain.models.Leaderboard
import com.ekotyoo.njawi.domain.models.Quiz
import com.ekotyoo.njawi.domain.models.Response
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun getQuizzesFromFirestore(): Flow<Response<List<Quiz>>>
    fun getQuizById(id: String): Flow<Response<Quiz>>
    fun getLeaderboardFromFirestore(): Flow<Response<List<Leaderboard>>>
    suspend fun addUserResultToFirestore(name: String, score: Int): Flow<Response<Void?>>
}