package com.example.marvelcomicsapp.di.module


import android.content.Context
import androidx.room.Room
import com.example.marvelcomicsapp.db.DaoUser
import com.example.marvelcomicsapp.db.MarvelDatabase
import com.example.marvelcomicsapp.repository.UserRepository
import com.example.marvelcomicsapp.db.UserMarvel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LoginModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, MarvelDatabase::class.java, "user_marvel"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: MarvelDatabase) = db.daoMarvel()

    @Provides
    fun provideEntity() = UserMarvel()

    @Provides
    @Singleton
    fun provideUserRepository(dao: DaoUser): UserRepository {
        return UserRepository(dao)
    }
}

