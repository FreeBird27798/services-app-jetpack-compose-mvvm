package com.taj.servicesapp.view.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.taj.servicesapp.model.bottombarnavigation.OrderData
import com.taj.servicesapp.ui.theme.GrayText

@Composable
fun OrderItem(order: OrderData) {
    Card(
        modifier = Modifier
            .padding(18.dp)
            .fillMaxWidth(),
        elevation = 5.dp,
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Order #${order.workId}",
                    modifier = Modifier.weight(1f),
                    color = Color.Black
                )
                Text(
                    text = order.updatedAt!!.substring(9),
                    color = GrayText,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )

            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Service Type: ${order.work?.name!!}")
        }
    }
}