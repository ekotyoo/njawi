package com.ekotyoo.njawi.domain.repository

import com.ekotyoo.njawi.domain.models.Materi
import com.ekotyoo.njawi.domain.models.Quiz
import com.ekotyoo.njawi.domain.models.Response
import kotlinx.coroutines.flow.Flow

interface MateriRepository {
    fun getMateriFromFirestore(): Flow<Response<List<Materi>>>

    fun getMateryById(materiId: String): Flow<Response<Materi?>>
}