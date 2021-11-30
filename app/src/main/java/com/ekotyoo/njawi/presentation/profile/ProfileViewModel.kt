package com.ekotyoo.njawi.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekotyoo.njawi.data.dto.AchievementDto
import com.ekotyoo.njawi.data.repository_impl.AchievementRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val achievementRepository: AchievementRepositoryImpl
) : ViewModel() {
    private val _achievements = MutableStateFlow<List<AchievementDto>>(emptyList())

    val achievements: StateFlow<List<AchievementDto>> = _achievements

    fun getAchievements() {
        viewModelScope.launch {
            achievementRepository.getAchievements.collect {
                _achievements.value = it
            }
        }
    }
}