package com.taj.servicesapp.view.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.taj.servicesapp.R
import com.taj.servicesapp.model.bottombarnavigation.ServiceData

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ServiceItem(service: ServiceData, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(93.dp).height(86.dp)
            .padding(top = 6.dp, bottom = 6.dp, start = 15.dp, end = 15.dp)
            .clickable { onItemClick.invoke() },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(0.5.dp, color = colorResource(id = R.color.item_border)),
        contentColor = Color.Black,
        backgroundColor = Color.White
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val painter = rememberImagePainter(service.icon)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Fit,
            )
            Text(text = service.name!!, color = Color.Blue)
        }
    }
}