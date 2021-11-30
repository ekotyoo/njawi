package com.ekotyoo.njawi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ekotyoo.njawi.data.dto.AchievementDto
import kotlinx.coroutines.flow.Flow

@Dao
interface AchievementDao {
    @Query("SELECT * FROM achievement_table ORDER BY id ASC")
    fun getAchievements(): Flow<List<AchievementDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAchievement(achievementDto: AchievementDto)
}