package com.ekotyoo.njawi.di

import android.content.Context
import androidx.room.Room
import com.ekotyoo.njawi.common.Constants.DATABASE_NAME
import com.ekotyoo.njawi.data.local.NjawiDatabase
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
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    fun provideMateriRepository(
        db: FirebaseFirestore,
    ): MateriRepository = MateriRepositoryImpl(db)

    @Provides
    fun provideQuizRepository(
        db: FirebaseFirestore,
    ): QuizRepository = QuizRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        NjawiDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideDao(database: NjawiDatabase) = database.achievementDao()
}