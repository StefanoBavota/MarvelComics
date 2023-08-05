package com.example.marvelcomicsapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaoUser {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(marvel: UserMarvel)
    @Query("SELECT * FROM user_marvel WHERE email=:email AND password= :password")
    suspend fun select(email:String,password:String): UserMarvel

    @Query("SELECT * FROM user_marvel WHERE idUser=:id")
    suspend fun selectFirst(id:Int):UserMarvel
}