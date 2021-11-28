package com.ekotyoo.njawi.domain.models

data class Materi(
    val id: String? = null,
    val title: String? = null,
    val chapters: List<Map<String, String>>? = null,
)
