package com.ekotyoo.njawi.data.dto

import com.ekotyoo.njawi.domain.models.Question

data class QuestionDto(
    val base_sentence: String,
    val question_name: String,
    val target_sentence: String
)
