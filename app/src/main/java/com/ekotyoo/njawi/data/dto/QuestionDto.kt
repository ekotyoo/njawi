package com.ekotyoo.njawi.data.dto

import com.ekotyoo.njawi.domain.models.Question

data class QuestionDto(
    val base_sentence: String,
    val question_name: String,
    val target_sentence: String
)

fun QuestionDto.toModel(): Question {
    return Question(
        base_sentence = base_sentence,
        question_name = question_name,
        target_sentence = target_sentence,
    )
}