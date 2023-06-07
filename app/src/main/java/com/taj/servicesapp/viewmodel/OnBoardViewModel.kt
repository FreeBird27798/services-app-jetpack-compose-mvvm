package com.taj.servicesapp.viewmodel

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.taj.servicesapp.R
import com.taj.servicesapp.data.DataStoreRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnboardViewModel(context: Context) : ViewModel() {

    private val repository: DataStoreRepository = DataStoreRepository(context)

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveOnBoardingState(completed = completed)
        }
    }

    private val _selectedTabIndex = mutableStateOf(0)
    val selectedTabIndex: State<Int> = _selectedTabIndex

    fun selectTab(index: Int) {
        _selectedTabIndex.value = index
    }

    fun getTabData(): List<OnboardTab> {
        return listOf(
            OnboardTab(
                title = "Tab 1",
                imageRes = R.drawable.on_boarding_01,
                text = "Fast reservation with technicians \n" +
                        "and craftsmen"
            ),
            OnboardTab(
                title = "Tab 2",
                imageRes = R.drawable.on_boarding_02,
                text = "Fast reservation with technicians \n" +
                        "and craftsmen"
            ),
            OnboardTab(
                title = "Tab 3",
                imageRes = R.drawable.on_boarding_01,
                text = "Fast reservation with technicians \n" +
                        "and craftsmen"
            )
        )
    }
}

data class OnboardTab(
    val title: String,
    @DrawableRes val imageRes: Int,
    val text: String
)