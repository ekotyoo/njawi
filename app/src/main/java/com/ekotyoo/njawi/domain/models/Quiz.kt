package com.ekotyoo.njawi.domain.models

data class Quiz(
    val question: Question,
    val theme: String,
    val type: String
)