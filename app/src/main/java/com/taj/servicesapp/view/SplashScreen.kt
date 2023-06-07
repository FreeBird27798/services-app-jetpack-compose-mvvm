package com.taj.servicesapp.view

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.taj.servicesapp.R
import com.taj.servicesapp.graphs.AuthScreen
import com.taj.servicesapp.ui.theme.Blue
import com.taj.servicesapp.ui.theme.DarkBlue
import com.taj.servicesapp.utils.setStatusBarGradiant
import com.taj.servicesapp.viewmodel.auth.SplashViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(splashViewModel: SplashViewModel, navController: NavHostController) {
    val context = LocalContext.current
    val activity = (context as? Activity)
    if (activity != null) {
        setStatusBarGradiant(activity, R.color.white,true)
    }
    LaunchedEffect(Unit) {
        delay(2000)
        val nextScreen = splashViewModel.startDestination.value
        navController.navigate(nextScreen) {
            popUpTo(AuthScreen.Splash.route) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        DarkBlue,
                        Blue
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(R.drawable.app_logo), contentDescription = "SVG Image")
    }
}

//private fun navigateToHome(navController: NavHostController) {
//    navController.popBackStack()
//    navController.navigate(Graph.HOME)
//}
//
//private fun navigateToOnboard(navController: NavHostController) {
//    navController.popBackStack()
//    navController.navigate(AuthScreen.Splash.route)
//}
