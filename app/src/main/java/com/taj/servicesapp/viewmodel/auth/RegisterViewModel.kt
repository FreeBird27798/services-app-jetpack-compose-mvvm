package com.taj.servicesapp.viewmodel.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taj.servicesapp.apiservice.ApiService
import com.taj.servicesapp.data.DataStoreRepository
import com.taj.servicesapp.model.auth.LoginRequest
import com.taj.servicesapp.model.auth.RegisterRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(context: Context) : ViewModel() {

    private val repository: DataStoreRepository = DataStoreRepository(context)
    //Todo: this can be used to check if the user is loggedIn or not
//    val isLoggedIn: Flow<Boolean> = repository.readLoginStatus()

    fun performRegister(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            val apiService = ApiService.geInstance()
            val registerResponse = apiService.register(registerRequest)

            if (registerResponse.success == true && registerResponse.data != null) {
                registerResponse.data!!.let { repository.saveUser(it) }
            }
        }
    }

}
