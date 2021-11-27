package com.ekotyoo.njawi.presentation.belajar.model

data class Belajar(
    val id: String,
    val title: String,
    val chapters: List<HashMap<String, Any>>
)