package com.ekotyoo.njawi.di

import com.ekotyoo.njawi.data.repository_impl.MateriRepositoryImpl
import com.ekotyoo.njawi.data.repository_impl.QuizRepositoryImpl
import com.ekotyoo.njawi.domain.repository.MateriRepository
import com.ekotyoo.njawi.domain.repository.QuizRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    fun provideMateriRef(db: FirebaseFirestore) = db.collection("materis")


    @Provides
    fun provideMateriQuery(materiRef: CollectionReference) = materiRef.orderBy("title")

    @Provides
    fun provideMateriRepository(
        materiRef: CollectionReference,
        materiQuery: Query,
    ): MateriRepository = MateriRepositoryImpl(materiRef, materiQuery)
}