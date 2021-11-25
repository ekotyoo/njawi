package com.ekotyoo.njawi.domain.models

data class Quiz(
    val theme: String,
    val questions: List<Question>,
)