package com.taj.servicesapp.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.taj.servicesapp.view.LoginScreen
import com.taj.servicesapp.view.OnboardScreen
import com.taj.servicesapp.view.RegisterScreen
import com.taj.servicesapp.view.SplashScreen
import com.taj.servicesapp.viewmodel.auth.LoginViewModel
import com.taj.servicesapp.viewmodel.OnboardViewModel
import com.taj.servicesapp.viewmodel.auth.RegisterViewModel
import com.taj.servicesapp.viewmodel.auth.SplashViewModel


fun NavGraphBuilder.authNavGraph(
    onboardViewModel: OnboardViewModel,
    splashViewModel: SplashViewModel,
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    navController: NavHostController
) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = splashViewModel.startDestination.value
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(loginViewModel,navController)
        }
        composable(route = AuthScreen.Register.route) {
            RegisterScreen(registerViewModel,navController)
        }
        composable(route = AuthScreen.Splash.route) {
            SplashScreen(splashViewModel, navController)
        }
        composable(route = AuthScreen.Onboard.route) {
            OnboardScreen(onboardViewModel, navController)
        }
    }
}


sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object Register : AuthScreen(route = "REGISTER")
    object Splash : AuthScreen(route = "SPLASH")
    object Onboard : AuthScreen(route = "ONBOARD")
}
