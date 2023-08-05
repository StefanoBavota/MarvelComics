package com.example.marvelcomicsapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.marvelcomicsapp.MyApp
import com.example.marvelcomicsapp.repository.MarvelRepository
import com.example.marvelcomicsapp.utils.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private var repository: MarvelRepository) : ViewModel() {
    private val _event = MutableLiveData<List<List<ViewModelCardHome>>>()

    var event : LiveData<List<List<ViewModelCardHome>>> = _event

    val preferencesManager: PreferencesManager
        get() = MyApp.INSTANCE.preferencesManager

    var userName=MutableLiveData<String>()

    fun getUser(){
        userName.value=preferencesManager.userName
    }

    fun getComicsData(c: String) {
        viewModelScope.launch {
            event = repository.getMarvelCharacters(c).asLiveData().map { response ->
                response.data.results.map { result ->
                    result.series.items.map { item ->
                        ViewModelCardHome(item.name)
                    }
                }
            }
        }
    }
}