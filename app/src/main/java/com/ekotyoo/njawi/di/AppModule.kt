package com.ekotyoo.njawi.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
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
    fun provideBooksRef(db: FirebaseFirestore) = db.collection("quizzes")

    @Provides
    fun provideQuizzesQuery(quizzesRef: CollectionReference) = quizzesRef.orderBy("level")
}