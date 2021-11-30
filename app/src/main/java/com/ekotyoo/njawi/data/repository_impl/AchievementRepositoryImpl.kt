package com.ekotyoo.njawi.data.repository_impl

import com.ekotyoo.njawi.data.dao.AchievementDao
import com.ekotyoo.njawi.data.dto.AchievementDto
import com.ekotyoo.njawi.domain.models.Achievement
import com.ekotyoo.njawi.domain.models.Response
import com.ekotyoo.njawi.domain.repository.AchievementRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class AchievementRepositoryImpl @Inject constructor( private val achievementDao: AchievementDao) {
    val getAchievements: Flow<List<AchievementDto>> = achievementDao.getAchievements()

    suspend fun addAchievement(achievementDto: AchievementDto) {
        achievementDao.addAchievement(achievementDto)
    }
}