package com.taj.servicesapp.bottomnavigationscreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.taj.servicesapp.BottomBarScreen
import com.taj.servicesapp.R
import com.taj.servicesapp.graphs.AuthScreen
import com.taj.servicesapp.graphs.Graph
import com.taj.servicesapp.ui.theme.Blue
import com.taj.servicesapp.ui.theme.DarkBlue00
import com.taj.servicesapp.ui.theme.GrayText
import com.taj.servicesapp.ui.theme.GrayWhite
import com.taj.servicesapp.ui.theme.SignOutIcon
import com.taj.servicesapp.viewmodel.auth.LoginViewModel

@Composable
fun UserScreen(loginViewModel: LoginViewModel, navController: NavHostController) {
    Box(
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Blue, DarkBlue00),
                    startX = 0f
                )
            )
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp),
            shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
            border = BorderStroke(1.dp, color = Color.Transparent),
            backgroundColor = Color.White
        ) {
            Column(
                modifier = Modifier.padding(top = 80.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "",
                    Modifier
                        .size(16.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    tint = DarkBlue00
                )
                Text(
                    text = "Taj Mahir B. Sabra",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                )
                Text(
                    text = "Gaza, Palestine",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = GrayText, modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(GrayWhite)
                )
                Column(
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                ) {
                    MoreItemRow(text = "Language")
                    MoreItemRow(text = "My Rates")
                    MoreItemRow(text = "Contact us")
                    MoreItemRow(text = "Share App")
                }
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(GrayWhite)
                )

                TextButton(
                    onClick = {
                        loginViewModel.performLogout()
                        navigateToLogin(navController)
                    }, modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sign_out),
                        contentDescription = "", Modifier.size(16.dp), tint = SignOutIcon
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "SIGN OUT", color = DarkBlue00)
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(GrayWhite)
                )
            }
        }
        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "Image",
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.TopCenter)
        )
    }

}


private fun navigateToLogin(navController: NavHostController) {
    navController.popBackStack()
    navController.navigate(Graph.AUTHENTICATION) {
        popUpTo(AuthScreen.Login.route) { inclusive = true }
    }
}

