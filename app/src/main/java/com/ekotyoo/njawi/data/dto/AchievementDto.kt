package com.ekotyoo.njawi.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ekotyoo.njawi.common.Constants.ACHIEVEMENT_TABLE

@Entity(tableName = ACHIEVEMENT_TABLE)
data class AchievementDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
)
