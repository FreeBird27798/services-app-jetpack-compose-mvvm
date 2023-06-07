package com.taj.servicesapp.viewmodel.bottombarnavigation

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taj.servicesapp.apiservice.ApiService
import com.taj.servicesapp.data.DataStoreRepository
import com.taj.servicesapp.model.bottombarnavigation.OrderData
import kotlinx.coroutines.launch

class OrderViewModel(context: Context) : ViewModel() {
    private val repository: DataStoreRepository = DataStoreRepository(context)

    val token = repository.readToken()

    //Completed Orders
    var allCompletedOrdersLiveData: List<OrderData> by mutableStateOf(listOf())

    fun getAllCompletedOrders() {

        viewModelScope.launch {
            val apiService = ApiService.geInstance()
            val getAllCompletedOrdersList = apiService.getCompletedOrders(token.toString())

            allCompletedOrdersLiveData = getAllCompletedOrdersList.data
        }
    }

    //Pending Orders
    var allPendingOrdersLiveData: List<OrderData> by mutableStateOf(listOf())

    fun getAllPendingOrders() {

        viewModelScope.launch {
            val apiService = ApiService.geInstance()
            val getAllPendingOrdersList = apiService.getPendingOrders(token.toString())

            allPendingOrdersLiveData = getAllPendingOrdersList.data
        }
    }

    //Un Completed Orders
    var allUnCompletedOrdersLiveData: List<OrderData> by mutableStateOf(listOf())

    fun getAllUnCompletedOrders() {

        viewModelScope.launch {
            val apiService = ApiService.geInstance()
            val getAllUnCompletedOrdersList = apiService.getUnCompletedOrders(token.toString())

            allUnCompletedOrdersLiveData = getAllUnCompletedOrdersList.data
        }
    }
}