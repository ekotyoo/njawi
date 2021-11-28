package com.ekotyoo.njawi.domain.models

data class Materi(
    val id: Int,
    val title: String,
    val chapters: List<Map<String, Any>>,
)
