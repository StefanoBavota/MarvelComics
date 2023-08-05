package com.example.marvelcomicsapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcomicsapp.MyApp
import com.example.marvelcomicsapp.db.UserMarvel
import com.example.marvelcomicsapp.repository.UserRepository
import com.example.marvelcomicsapp.utils.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var repository: UserRepository) : ViewModel() {

    val preferencesManager: PreferencesManager
        get() = MyApp.INSTANCE.preferencesManager

    var logShare = MutableLiveData<Boolean>()

    var nameShare = MutableLiveData<String>()

    var userLive = MutableLiveData<UserMarvel?>()

    fun select(email: String, password: String) {
        viewModelScope.launch {
            userLive.value = repository.select(email, password)
        }
    }

    fun changeCredential(boolean: Boolean) {
        preferencesManager.credentialUser = boolean
    }

    fun update() {
        preferencesManager.userPassword = userLive.value?.password
        preferencesManager.userEmail = userLive.value?.email
        preferencesManager.userName = userLive.value?.name
        preferencesManager.isUserLogged = true
        nameShare.value = preferencesManager.userName
        logShare.value = preferencesManager.isUserLogged
    }

    fun biometric() {
        viewModelScope.launch {
            userLive.value = repository.selectFirst()
            preferencesManager.isUserLogged = false
            preferencesManager.userPassword = userLive.value?.password
            preferencesManager.userEmail = userLive.value?.email
            preferencesManager.userName = userLive.value?.name
            nameShare.value = preferencesManager.userName
            logShare.value = preferencesManager.isUserLogged
        }
    }

    fun logout() {
        userLive.value = null
        preferencesManager.isUserLogged = false
        logShare.value = preferencesManager.isUserLogged
        preferencesManager.userName = ""
    }


    fun insertUser(user: UserMarvel) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(user)
        }
}

