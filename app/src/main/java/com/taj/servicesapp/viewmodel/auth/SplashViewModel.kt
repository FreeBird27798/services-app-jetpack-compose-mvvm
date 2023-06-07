package com.taj.servicesapp.viewmodel.auth

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taj.servicesapp.data.DataStoreRepository
import com.taj.servicesapp.graphs.AuthScreen
import com.taj.servicesapp.graphs.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SplashViewModel(context: Context) : ViewModel() {

    private val repository: DataStoreRepository = DataStoreRepository(context)

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(AuthScreen.Splash.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            repository.readOnBoardingState().collect { completed ->
                if (completed) {
                    repository.readLoginStatus().collect { isLoggedIn ->
                        if (isLoggedIn) {
                            _startDestination.value = Graph.HOME
                        } else {
                            _startDestination.value = AuthScreen.Login.route
                        }
                    }
                } else {
                    _startDestination.value = AuthScreen.Onboard.route
                }
                _isLoading.value = false
            }
        }
    }
}
