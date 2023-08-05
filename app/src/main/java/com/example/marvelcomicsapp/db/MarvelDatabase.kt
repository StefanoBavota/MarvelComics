package com.example.marvelcomicsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserMarvel::class], version = 3)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun daoMarvel() : DaoUser
}