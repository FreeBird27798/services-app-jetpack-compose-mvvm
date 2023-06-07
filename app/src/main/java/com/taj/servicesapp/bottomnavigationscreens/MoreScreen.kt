package com.taj.servicesapp.bottomnavigationscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taj.servicesapp.BottomBarScreen
import com.taj.servicesapp.view.widgets.CurvedBox

@Composable
fun MoreScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CurvedBox(
            height = 40.dp,
            curveEndYRatio = 0.70f,
            curveControlYRatio = 1.1f,
            x1 = 0.60f,
            x2 = 0.40f,
        )
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        ) {
            MoreItemRow("Change Password")
            MoreItemRow("FAQ's")
            MoreItemRow("About App")
            MoreItemRow("Terms & Conditions")
            MoreItemRow("Privacy Policy")
            MoreItemRow("Rate App")
            MoreItemRow("Delete Account")
        }
    }
}

@Composable
fun MoreItemRow(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 35.dp),
    ) {
        Text(text = text, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = null,
            modifier = Modifier.size(12.dp),
            tint = Color.Gray
        )
    }
}