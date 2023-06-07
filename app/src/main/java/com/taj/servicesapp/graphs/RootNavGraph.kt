package com.taj.servicesapp.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.taj.servicesapp.view.HomeScreen
import com.taj.servicesapp.viewmodel.auth.LoginViewModel
import com.taj.servicesapp.viewmodel.OnboardViewModel
import com.taj.servicesapp.viewmodel.auth.RegisterViewModel
import com.taj.servicesapp.viewmodel.auth.SplashViewModel
import com.taj.servicesapp.viewmodel.bottombarnavigation.OrderViewModel
import com.taj.servicesapp.viewmodel.bottombarnavigation.ServiceViewModel

@Composable
fun RootNavGraph(
    onboardViewModel: OnboardViewModel,
    splashViewModel: SplashViewModel,
    loginViewModel: LoginViewModel,
    orderViewModel: OrderViewModel,
    registerViewModel: RegisterViewModel,
    serviceViewModel: ServiceViewModel,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(
            splashViewModel = splashViewModel,
            onboardViewModel = onboardViewModel,
            loginViewModel = loginViewModel,
            registerViewModel = registerViewModel,
            navController = navController
        )
        composable(route = Graph.HOME) {
            HomeScreen(loginViewModel, orderViewModel, serviceViewModel)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
}
