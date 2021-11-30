package com.ekotyoo.njawi.domain.repository

import com.ekotyoo.njawi.data.dto.AchievementDto
import com.ekotyoo.njawi.domain.models.Response
import kotlinx.coroutines.flow.Flow

interface AchievementRepository {
    val getAchievements: Flow<Response<List<AchievementDto>>>
    suspend fun addAchievement(achievementDto: AchievementDto)
}