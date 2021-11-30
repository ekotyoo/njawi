package com.ekotyoo.njawi.domain.models

data class Quiz(
    val id: String? = null,
    val theme: String? = null,
    val level: String? = null,
    val questions: List<Question>? = null,
)