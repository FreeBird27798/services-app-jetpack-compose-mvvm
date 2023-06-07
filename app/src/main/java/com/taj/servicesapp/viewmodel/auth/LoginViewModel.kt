package com.taj.servicesapp.viewmodel.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taj.servicesapp.apiservice.ApiService
import com.taj.servicesapp.data.DataStoreRepository
import com.taj.servicesapp.model.auth.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(context: Context) : ViewModel() {

    private val repository: DataStoreRepository = DataStoreRepository(context)
    //Todo: this can be used to check if the user is loggedIn or not
//    val isLoggedIn: Flow<Boolean> = repository.readLoginStatus()

    fun performLogin(loginRequest: LoginRequest) {
        viewModelScope.launch {
            val apiService = ApiService.geInstance()
            val loginResponse = apiService.login(loginRequest)

            if (loginResponse.success == true && loginResponse.data != null) {
                loginResponse.data!!.let { repository.saveUser(it) }
            }
        }
    }

    fun performLogout() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearUserData()
        }
    }
}
