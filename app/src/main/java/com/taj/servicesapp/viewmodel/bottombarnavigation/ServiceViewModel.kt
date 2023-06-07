package com.taj.servicesapp.viewmodel.bottombarnavigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taj.servicesapp.apiservice.ApiService
import com.taj.servicesapp.model.bottombarnavigation.ServiceData
import kotlinx.coroutines.launch

class ServiceViewModel : ViewModel() {

    var allServicesLiveData: List<ServiceData> by mutableStateOf(listOf())

    fun getAllServices() {

        viewModelScope.launch {
            val apiService = ApiService.geInstance()
            val getAllServicesList = apiService.getAllServices()

            allServicesLiveData = getAllServicesList.data
        }
    }
}