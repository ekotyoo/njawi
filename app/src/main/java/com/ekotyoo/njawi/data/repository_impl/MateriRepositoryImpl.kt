package com.ekotyoo.njawi.data.repository_impl

import com.ekotyoo.njawi.domain.models.Materi
import com.ekotyoo.njawi.domain.models.Response
import com.ekotyoo.njawi.domain.repository.MateriRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ViewModelScoped
class MateriRepositoryImpl @Inject constructor(
    private val materiRef: CollectionReference,
    private val materiQuery: Query
): MateriRepository {
    @ExperimentalCoroutinesApi
    override fun getMateriFromFirestore() = callbackFlow {
        val snapshotListener = materiQuery.get().addOnSuccessListener { result ->
            val response = if (result != null) {
                val materis = result.documents.map {
                    Materi(
                        id = it.id,
                        title = it["title"] as String,
                        chapters = it["chapters"] as List<Map<String, String>>?
                    )
                }
                Response.Success(materis)
            } else {
                Response.Error("Something went wrong")
            }
            trySend(response).isSuccess
        }
        awaitClose {

        }
    }

    @ExperimentalCoroutinesApi
    override fun getMateryById(materiId: String) = callbackFlow {
        val snapshotListener = materiRef.document(materiId).get().addOnSuccessListener { result ->
            val response = if (result != null) {
                val materi = result.toObject(Materi::class.java)
                Response.Success(materi)
            } else {
                Response.Error("Something went wrong")
            }
            trySend(response).isSuccess
        }
        awaitClose {

        }
    }
}