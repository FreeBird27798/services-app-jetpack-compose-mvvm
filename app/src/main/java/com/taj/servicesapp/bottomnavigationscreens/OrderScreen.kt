package com.taj.servicesapp.bottomnavigationscreens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taj.servicesapp.model.bottombarnavigation.OrderData
import com.taj.servicesapp.ui.theme.DarkBlue00
import com.taj.servicesapp.ui.theme.GrayText
import com.taj.servicesapp.ui.theme.TabColor
import com.taj.servicesapp.view.items.OrderItem
import com.taj.servicesapp.viewmodel.bottombarnavigation.OrderViewModel
import kotlinx.coroutines.launch
import java.time.Duration

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderScreen(orderViewModel: OrderViewModel) {
    val tabTitles = listOf("Pending", "Underway", "Completed")
    val selectedTabIndex = remember { mutableStateOf(0) }

    val completedOrders = orderViewModel.allCompletedOrdersLiveData
    val pendingOrders = orderViewModel.allPendingOrdersLiveData
    val unCompletedOrders = orderViewModel.allUnCompletedOrdersLiveData

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        try {
            orderViewModel.getAllPendingOrders()
            orderViewModel.getAllUnCompletedOrders()
            orderViewModel.getAllCompletedOrders()
        } catch (e: Exception) {
            coroutineScope.launch {
                snackBarHostState.showSnackbar(e.message ?: "An error occurred")
            }
        }
    }


    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            indicator = { tabPositions ->
                if (selectedTabIndex.value >= 0 && selectedTabIndex.value < tabPositions.size) {
                    TabRowDefaults.Indicator(
                        color = DarkBlue00,
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex.value])
                            .padding(start = 20.dp, end = 20.dp)
                    )
                }
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex.value == index,
                    onClick = { selectedTabIndex.value = index },
                    text = {
                        Text(
                            title, color =
                            if (selectedTabIndex.value == index) {
                                DarkBlue00
                            } else {
                                TabColor
                            }
                        )
                    }
                )
            }
        }

        when (selectedTabIndex.value) {
            0 -> {
                if (pendingOrders.isNotEmpty()) {
                    TabContent(pendingOrders)
                } else {
                    TabContentPlaceHolder("Pending")
                }
            }

            1 -> {
                if (unCompletedOrders.isNotEmpty()) {
                    TabContent(unCompletedOrders)
                } else {
                    TabContentPlaceHolder("Underway")
                }
            }

            2 -> {
                if (completedOrders.isNotEmpty()) {
                    TabContent(completedOrders)
                } else {
                    TabContentPlaceHolder("Completed")
                }
            }
        }
    }
}


@Composable
fun TabContent(orders: List<OrderData>) {
    LazyColumn {
        items(orders) {
            OrderItem(it)
        }
    }
}

@Composable
fun TabContentPlaceHolder(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = "No $text Orders",
            color = GrayText,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


