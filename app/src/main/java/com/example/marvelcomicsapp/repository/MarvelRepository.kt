package com.example.marvelcomicsapp.repository


import com.example.marvelcomicsapp.core.network.base.network.ApiService
import com.example.marvelcomicsapp.core.network.base.network.Constants.API_KEY
import com.example.marvelcomicsapp.core.network.base.network.Constants.HASH
import com.example.marvelcomicsapp.core.network.base.network.Constants.TS
import com.example.marvelcomicsapp.core.network.base.network.MarvelCharacterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarvelRepository @Inject constructor(private val apiService: ApiService){

    fun getMarvelCharacters(name: String): Flow<MarvelCharacterResponse> = flow {
        val siteResponse = apiService.getCharactersResponse(name,TS, API_KEY, HASH)
        siteResponse.body()?.let { emit(it) }
    }
}


