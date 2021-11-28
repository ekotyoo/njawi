package com.ekotyoo.njawi.data.repository_impl

import com.ekotyoo.njawi.domain.models.Materi
import com.ekotyoo.njawi.domain.models.Response
import com.ekotyoo.njawi.domain.repository.MateriRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
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
        val snapshotListener = materiQuery.addSnapshotListener { snapshot, e ->
            val response = if (snapshot != null) {
                val materis = snapshot.toObjects(Materi::class.java)
                Response.Success(materis)
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