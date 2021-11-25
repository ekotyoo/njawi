package com.ekotyoo.njawi.common

import com.ekotyoo.njawi.domain.models.Question
import com.ekotyoo.njawi.domain.models.Quiz

object Constants {
    fun getQuiz(): Quiz {
        val questions: MutableList<Question> = mutableListOf<Question>()
        questions.add(Question(targetSentence = "bapak sare kula adus1", baseSentence = "ayah tidur saya mandi1"))
        questions.add(Question(targetSentence = "bapak sare kula adus2", baseSentence = "ayah tidur saya mandi2"))
        questions.add(Question(targetSentence = "bapak sare kula adus3", baseSentence = "ayah tidur saya mandi3"))
        questions.add(Question(targetSentence = "bapak sare kula adus4", baseSentence = "ayah tidur saya mandi4"))
        questions.add(Question(targetSentence = "bapak sare kula adus5", baseSentence = "ayah tidur saya mandi5"))
        return Quiz(theme = "Krama Inggil", questions = questions)
    }
}