package com.ekotyoo.njawi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ekotyoo.njawi.data.dao.AchievementDao
import com.ekotyoo.njawi.data.dto.AchievementDto

@Database(entities = [AchievementDto::class], version = 1, exportSchema = false)
abstract class NjawiDatabase: RoomDatabase() {
    abstract fun achievementDao(): AchievementDao
}