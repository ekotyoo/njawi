package com.ekotyoo.njawi.data.repository_impl

import com.ekotyoo.njawi.domain.models.Quiz
import com.ekotyoo.njawi.domain.models.Response
import com.ekotyoo.njawi.domain.repository.QuizRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ViewModelScoped
class QuizRepositoryImpl @Inject constructor(
    private val quizzesRef: CollectionReference,
    private val quizzesQuery: Query
): QuizRepository {
    @ExperimentalCoroutinesApi
    override fun getQuizzesFromFirestore() = callbackFlow {
        val snapshotListener = quizzesQuery.addSnapshotListener { snapshot, e ->
            val response = if (snapshot != null) {
                val quizzes = snapshot.toObjects(Quiz::class.java)
                Response.Success(quizzes)
            } else {
                Response.Error(e?.message ?: e.toString())
            }
            trySend(response).isSuccess
        }
        awaitClose {
            snapshotListener.remove()
        }
    }
}