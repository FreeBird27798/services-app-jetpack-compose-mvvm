package com.taj.servicesapp.bottomnavigationscreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taj.servicesapp.R
import com.taj.servicesapp.view.items.ServiceItem
import com.taj.servicesapp.view.widgets.CurvedBox
import com.taj.servicesapp.viewmodel.bottombarnavigation.ServiceViewModel

@Composable
fun ServiceScreen(serviceViewModel: ServiceViewModel) {

    val allServices = serviceViewModel.allServicesLiveData

    LaunchedEffect(Unit) {
        serviceViewModel.getAllServices()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 40.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CurvedBox(content = {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .width(300.dp)
                    .height(87.dp),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, color = Color.Transparent),
                backgroundColor = Color.White
            ) {
            }
        })
        Text(
            text = "Select Service",
            color = colorResource(id = R.color.dark_blue_00),
            style = TextStyle(
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        when {
            allServices.isEmpty() -> {
                CircularProgressIndicator()
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3)
        ) {
            items(allServices) { service ->
                ServiceItem(service = service) {
                }
            }
        }
    }

}






