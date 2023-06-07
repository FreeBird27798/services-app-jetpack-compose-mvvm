package com.taj.servicesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.taj.servicesapp.ui.theme.ServicesAppTheme
import androidx.navigation.compose.rememberNavController
import com.taj.servicesapp.graphs.RootNavGraph
import com.taj.servicesapp.utils.setStatusBarGradiant
import com.taj.servicesapp.viewmodel.auth.LoginViewModel
import com.taj.servicesapp.viewmodel.OnboardViewModel
import com.taj.servicesapp.viewmodel.auth.RegisterViewModel
import com.taj.servicesapp.viewmodel.auth.SplashViewModel
import com.taj.servicesapp.viewmodel.bottombarnavigation.OrderViewModel
import com.taj.servicesapp.viewmodel.bottombarnavigation.ServiceViewModel


class MainActivity : ComponentActivity() {

    private lateinit var splashViewModel: SplashViewModel
    private lateinit var onboardViewModel: OnboardViewModel
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var serviceViewModel: ServiceViewModel
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var orderViewModel: OrderViewModel

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewModel = SplashViewModel(this)
        onboardViewModel = OnboardViewModel(this)
        loginViewModel = LoginViewModel(this)
        serviceViewModel = ServiceViewModel()
        registerViewModel = RegisterViewModel(this)
        orderViewModel = OrderViewModel(this)

        setContent {
            setStatusBarGradiant(this, R.color.dark_blue)
            RootNavGraph(
                splashViewModel = splashViewModel,
                onboardViewModel = onboardViewModel,
                loginViewModel = loginViewModel,
                orderViewModel = orderViewModel,
                serviceViewModel = serviceViewModel,
                registerViewModel = registerViewModel,
                navController = rememberNavController()
            )
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ServicesAppTheme {
        Greeting("Android")
    }
}