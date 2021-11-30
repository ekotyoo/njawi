package com.ekotyoo.njawi.data.repository_impl

import com.ekotyoo.njawi.domain.models.Question
import com.ekotyoo.njawi.domain.models.Quiz
import com.ekotyoo.njawi.domain.models.Response
import com.ekotyoo.njawi.domain.repository.QuizRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class QuizRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
): QuizRepository {
    @ExperimentalCoroutinesApi
    override fun getQuizzesFromFirestore() = callbackFlow {
        val snapshotListener = db.collection("quizzes").get().addOnSuccessListener { result ->
            val response = if (result != null) {
                val quizzes = result.map { document ->
                    val questionsMap = document["questions"] as List<Map<String, Any>>?
                    Quiz(
                        id = document.id,
                        theme = document["theme"] as String?,
                        level = document["level"] as String?,
                        questions = questionsMap!!.map {
                            Question(
                                baseSentence = it["baseSentence"] as String,
                                targetSentence = it["targetSentence"] as String,
                            )
                        }
                    )
                }
                Response.Success(quizzes)
            } else {
                Response.Error("Something went wrong")
            }
            trySend(response).isSuccess
        }
        awaitClose {

        }
    }

    @ExperimentalCoroutinesApi
    override fun getQuizById(id: String) = callbackFlow {
        val snapshotListener = db.collection("quizzes").document(id).get().addOnSuccessListener { result ->
            val response = if (result != null) {
                val questionsMap = result["questions"] as List<Map<String, Any>>?
                val quiz = Quiz(
                    id = result.id,
                    theme = result["theme"] as String,
                    level = result["level"] as String,
                    questions = questionsMap!!.map {
                        Question(
                            baseSentence = it["baseSentence"] as String,
                            targetSentence = it["targetSentence"] as String
                        )
                    }
                )
                Response.Success(quiz)
            } else {
                Response.Error("Something went wrong")
            }
            trySend(response).isSuccess
        }
        awaitClose {  }
    }
}