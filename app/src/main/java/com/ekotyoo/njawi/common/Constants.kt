package com.ekotyoo.njawi.common

import com.ekotyoo.njawi.domain.models.Question
import com.ekotyoo.njawi.domain.models.Quiz

object Constants {
    fun getQuiz(): Quiz {
        val questions: MutableList<Question> = mutableListOf<Question>()
        questions.add(Question(targetSentence = "bapak sare kula adus1", baseSentence = "ayah tidur saya mandi1"))
        questions.add(Question(targetSentence = "simbah lagi dhahar sambel mlinjo", baseSentence = "nenek sedang makan sambel mlinjo"))
        questions.add(Question(targetSentence = "pakdhe tindhak dateng peken", baseSentence = "paman pergi ke pasar"))
        questions.add(Question(targetSentence = "ibu lagi masak gulai babi", baseSentence = "ayah tidur saya mandi4"))
        questions.add(Question(targetSentence = "budi nginum arak jawa", baseSentence = "budi minum arak jawa"))
        questions.add(Question(targetSentence = "eko dolanan manuk", baseSentence = "eko bermain burung"))
        questions.add(Question(targetSentence = "simbah sakit paningal", baseSentence = "nenek sakit mata"))
        return Quiz(level = "Dasar", theme = "Krama Inggil", questions = questions)
    }

    const val DATABASE_NAME = "njawi_database"
    const val ACHIEVEMENT_TABLE = "achievement_table"

    const val PREFS_NAME = "com.ekotyoo.njawi"
    const val PREF_VERSION_CODE_KEY = "1.0"
    const val DOESNT_EXIST = -1
}