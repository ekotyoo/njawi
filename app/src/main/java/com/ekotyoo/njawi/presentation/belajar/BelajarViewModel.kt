package com.ekotyoo.njawi.presentation.belajar

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekotyoo.njawi.domain.models.Materi
import com.ekotyoo.njawi.domain.models.Response
import com.ekotyoo.njawi.domain.repository.MateriRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BelajarViewModel @Inject constructor(
    private val repository: MateriRepository
) : ViewModel() {
    private val _materisState = mutableStateOf<Response<List<Materi>>>(Response.Loading)
    val materisState: State<Response<List<Materi>>> = _materisState

    init {
        getMateris()
    }

    private fun getMateris() {
        viewModelScope.launch {
            repository.getMateriFromFirestore().collect { response ->
                _materisState.value = response
            }
        }
    }
}