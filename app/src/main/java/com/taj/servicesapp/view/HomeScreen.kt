package com.taj.servicesapp.view


import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.taj.servicesapp.BottomBarScreen
import com.taj.servicesapp.R
import com.taj.servicesapp.graphs.BottomNavGraph
import com.taj.servicesapp.ui.theme.Blue
import com.taj.servicesapp.ui.theme.DarkBlue00
import com.taj.servicesapp.utils.setStatusBarGradiant
import com.taj.servicesapp.viewmodel.OnboardViewModel
import com.taj.servicesapp.viewmodel.auth.LoginViewModel
import com.taj.servicesapp.viewmodel.auth.RegisterViewModel
import com.taj.servicesapp.viewmodel.auth.SplashViewModel
import com.taj.servicesapp.viewmodel.bottombarnavigation.OrderViewModel
import com.taj.servicesapp.viewmodel.bottombarnavigation.ServiceViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    loginViewModel: LoginViewModel,
    orderViewModel: OrderViewModel,
    serviceViewModel: ServiceViewModel
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val activity = (context as? Activity)
    if (activity != null) {
        setStatusBarGradiant(activity, R.drawable.gradient_theme, hideNavigationBar = false)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Blue, DarkBlue00)
                        )
                    )
            ) {
                TopAppBar(
                    contentColor = colorResource(id = R.color.white),
                    title = {
                        var title = ""
                        if (currentDestination?.route == BottomBarScreen.Order.route) {
                            title = BottomBarScreen.Order.title
                        } else if (currentDestination?.route == BottomBarScreen.More.route) {
                            title = BottomBarScreen.More.title
                        }
                        Row(
                            Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                text = title
                            )
                        }
                    },
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp,
                    actions = {
                        if (currentDestination?.route == BottomBarScreen.Service.route) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.app_logo),
                                    contentDescription = "App Logo",
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(48.dp)
                                )

                                IconButton(
                                    onClick = { /* TODO: Handle notification icon click */ },
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_notification),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(24.dp)
                                            .width(24.dp), tint = Color.White
                                    )
                                }
                            }
                        } else if (currentDestination?.route == BottomBarScreen.User.route) {
                            IconButton(
                                onClick = { /* TODO: Handle notification icon click */ },
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_settings),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(24.dp)
                                        .width(24.dp), tint = Color.White
                                )
                            }

                        }
                    }
                )
            }
        },
        content = {
            BottomNavGraph(
                loginViewModel = loginViewModel,
                orderViewModel = orderViewModel,
                serviceViewModel = serviceViewModel,
                navController = navController
            )
        },
        bottomBar = { BottomBar(navController) }
    )
}

@Composable
fun BottomBar(
    navController: NavHostController
) {

    val screens = listOf(
        BottomBarScreen.Service,
        BottomBarScreen.Order,
        BottomBarScreen.User,
        BottomBarScreen.More,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = Blue
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = { Text(text = screen.title, color = Color.White) },
        icon = {
            Icon(
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp),
                tint = Color.White,
                painter = painterResource(id = screen.icon),
                contentDescription = null
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}