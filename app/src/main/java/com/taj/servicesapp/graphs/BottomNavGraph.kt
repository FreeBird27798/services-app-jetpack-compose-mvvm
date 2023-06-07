package com.taj.servicesapp.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.taj.servicesapp.BottomBarScreen
import com.taj.servicesapp.bottomnavigationscreens.UserScreen
import com.taj.servicesapp.bottomnavigationscreens.MoreScreen
import com.taj.servicesapp.bottomnavigationscreens.OrderScreen
import com.taj.servicesapp.bottomnavigationscreens.ServiceScreen
import com.taj.servicesapp.viewmodel.OnboardViewModel
import com.taj.servicesapp.viewmodel.auth.LoginViewModel
import com.taj.servicesapp.viewmodel.auth.RegisterViewModel
import com.taj.servicesapp.viewmodel.auth.SplashViewModel
import com.taj.servicesapp.viewmodel.bottombarnavigation.OrderViewModel
import com.taj.servicesapp.viewmodel.bottombarnavigation.ServiceViewModel

@Composable
fun BottomNavGraph(
    loginViewModel: LoginViewModel,
    orderViewModel: OrderViewModel,
    serviceViewModel: ServiceViewModel,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Service.route
    ) {
        composable(BottomBarScreen.Service.route) {
            ServiceScreen(serviceViewModel)
        }
        composable(BottomBarScreen.Order.route) {
            OrderScreen(orderViewModel)
        }
        composable(BottomBarScreen.User.route) {
            UserScreen(loginViewModel, navController)
        }
        composable(BottomBarScreen.More.route) {
            MoreScreen()
        }
    }
}
