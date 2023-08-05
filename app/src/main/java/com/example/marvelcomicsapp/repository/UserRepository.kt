package com.example.marvelcomicsapp.repository

import com.example.marvelcomicsapp.db.DaoUser
import com.example.marvelcomicsapp.db.UserMarvel
import javax.inject.Inject

class UserRepository @Inject constructor(private var dao: DaoUser) {
    suspend fun select(email: String, password: String): UserMarvel {
        return dao.select(email, password)
    }

    suspend fun insertUser(user: UserMarvel) {
        dao.insert(user)
    }

    suspend fun selectFirst():UserMarvel{
        return dao.selectFirst(1)
    }
}