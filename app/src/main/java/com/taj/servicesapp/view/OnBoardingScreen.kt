package com.taj.servicesapp.view

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.taj.servicesapp.R
import com.taj.servicesapp.graphs.AuthScreen
import com.taj.servicesapp.ui.theme.Blue
import com.taj.servicesapp.ui.theme.DarkBlue
import com.taj.servicesapp.utils.setStatusBarGradiant
import com.taj.servicesapp.view.widgets.MyButton
import com.taj.servicesapp.viewmodel.OnboardViewModel

@Composable
fun OnboardScreen(
    onboardViewModel: OnboardViewModel,
    navController: NavHostController
) {

    val context = LocalContext.current
    val activity = (context as? Activity)
    if (activity != null) {
        setStatusBarGradiant(activity, R.color.white)
    }

    val tabData = remember { onboardViewModel.getTabData() }
    var selectedTabIndex by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .width(200.dp)
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(tabData[selectedTabIndex].imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = tabData[selectedTabIndex].text,
            style = MaterialTheme.typography.body1.copy(
                color = colorResource(id = R.color.blue_text_color),
                fontSize = 21.sp,
                fontWeight = FontWeight.SemiBold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(140.dp))

        MyButton(
            text = if (selectedTabIndex < tabData.size - 1) "Next" else "Finish",
            onClick = {
                if (selectedTabIndex < tabData.size - 1) {
                    selectedTabIndex++
                } else {
                    onboardViewModel.saveOnBoardingState(completed = true)
                    navigateToLogin(navController)
                }
            },
            gradientColors = listOf(
                DarkBlue,
                Blue
            )
        )
    }
}

private fun navigateToLogin(navController: NavHostController) {
    navController.popBackStack()
    navController.navigate(AuthScreen.Login.route)
}

