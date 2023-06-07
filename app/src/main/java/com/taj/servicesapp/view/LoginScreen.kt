package com.taj.servicesapp.view

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.taj.servicesapp.R
import com.taj.servicesapp.graphs.AuthScreen
import com.taj.servicesapp.graphs.Graph
import com.taj.servicesapp.model.auth.LoginRequest
import com.taj.servicesapp.ui.theme.Blue
import com.taj.servicesapp.ui.theme.DarkBlue
import com.taj.servicesapp.utils.setStatusBarGradiant
import com.taj.servicesapp.view.widgets.MyButton
import com.taj.servicesapp.viewmodel.auth.LoginViewModel
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavHostController) {
    val tabTitles = listOf("Service Provider", "Customer")
    val selectedTab = remember { mutableStateOf(0) }
    val context = LocalContext.current
    val activity = (context as? Activity)
    if (activity != null) {
        setStatusBarGradiant(activity, R.drawable.gradient_login_theme, hideNavigationBar = false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(DarkBlue, Blue),
                    startX = 0f,
                    endX = Float.POSITIVE_INFINITY
                )
            )
    ) {


        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "background",
                Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .padding(top = 30.dp)
            )

            Card(
                shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp)
            ) {
                Column(modifier = Modifier.padding(15.dp)) {

                    TabRow(
                        backgroundColor = Color.White, contentColor = Color.Gray,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                color = Color(0xff346EDF),
                                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab.value])
                            )
                        },
                        selectedTabIndex = selectedTab.value,
                    ) {
                        tabTitles.forEachIndexed { index, title ->
                            Tab(selected = selectedTab.value == index,
                                selectedContentColor = Color(0xff346EDF),
                                unselectedContentColor = Color(0xff6FC8FB),
                                onClick = { selectedTab.value = index },
                                text = {
                                    Text(
                                        text = title,
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily.Monospace,
                                    )
                                })
                        }

                    }

                    when (selectedTab.value) {
                        0 -> {

                            var isVisible by remember { mutableStateOf(false) }

                            LaunchedEffect(Unit) {
                                // Start the timer when the composable is first composed
                                isVisible = false

                                // Delay for 2 seconds
                                delay(0)

                                // After the delay, hide the view
                                isVisible = true
                            }

                            AnimatedVisibility(
                                visible = isVisible,
                                enter = slideInHorizontally(
                                    initialOffsetX = { 100 }, // Initial offset to the left
                                    animationSpec = TweenSpec(300) // Animation duration
                                ),
                            ) {
                                LoginView(loginViewModel, navController)
                            }
                        }

                        1 -> {

                            var isVisible by remember { mutableStateOf(false) }

                            LaunchedEffect(Unit) {
                                // Start the timer when the composable is first composed
                                isVisible = false

                                // Delay for 2 seconds
                                delay(0)

                                // After the delay, hide the view
                                isVisible = true
                            }

                            AnimatedVisibility(
                                visible = isVisible,
                                enter = slideInHorizontally(
                                    initialOffsetX = { -100 }, // Initial offset to the left
                                    animationSpec = TweenSpec(300) // Animation duration
                                ),
                            ) {
                                LoginView(loginViewModel, navController)
                            }


                        }
                    }

                }


            }
        }
    }

}

@Composable
fun LoginView(loginViewModel: LoginViewModel, navController: NavHostController) {

    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    val rememberMeState = remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = {
                Text(
                    "Email",
                    Modifier.padding(2.dp),
                    fontSize = 10.sp, fontFamily = FontFamily.Monospace,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .background(Color.LightGray)
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)),
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            textStyle = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Start
            )

        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = {
                Text(
                    "Password",
                    Modifier.padding(2.dp),
                    Color.Gray,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace,


                    )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)),
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            textStyle = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Start
            )

        )
        Spacer(modifier = Modifier.height(16.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Checkbox(
                checked = rememberMeState.value,
                onCheckedChange = { rememberMeState.value = it },
            )


            Text(
                text = "Remember Me",
                color = Color.Black,
                style = MaterialTheme.typography.body1,
                fontSize = 12.sp, fontFamily = FontFamily.Monospace,


                )
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )

            Text(
                text = "Forgot Password?",
                fontSize = 15.sp,
                color = Color.Black,
                fontFamily = FontFamily.Monospace,
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    start = 0.dp,
                    end = 0.dp,
                    bottom = 0.dp,
                ),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Column(
                modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = "New Member? ",
                    color = Color.Black,
                    fontSize = 16.sp,
                )

                TextButton(onClick = { navigateToSignUp(navController) }) {
                    Text(
                        text = "SIGN UP",
                        fontSize = 16.sp,
                        color = Color.Blue,
                        textAlign = TextAlign.Start,
                        textDecoration = TextDecoration.Underline
                    )
                }

            }

            MyButton(
                text = "Login",
                onClick = {
                    loginViewModel.performLogin(
                        LoginRequest(
                            email = emailState.value.text,
                            password = passwordState.value.text
                        )
                    )
                    navigateToHome(navController = navController)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(64.dp),
                gradientColors = listOf(
                    DarkBlue, Blue
                )
            )

        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,

            ) {

            val context = LocalContext.current

            TextButton(onClick = {
                navigateToHome(navController = navController)
            }) {
                Text(
                    text = "Get Start Now", fontSize = 18.sp, fontFamily = FontFamily.Monospace,
                    color = Color.Black, modifier = Modifier.padding(8.dp)
                )

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "icon",
                    tint = Color.Black
                )

            }


        }
    }

}

private fun navigateToHome(navController: NavHostController) {
    navController.popBackStack()
    navController.navigate(Graph.HOME)
}

private fun navigateToSignUp(navController: NavHostController) {
    navController.popBackStack()
    navController.navigate(AuthScreen.Register.route)
}

