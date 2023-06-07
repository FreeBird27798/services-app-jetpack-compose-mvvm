package com.taj.servicesapp.utils

import android.app.Activity
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat

@Composable
fun HideStatusBar() {
    val context = LocalContext.current

    AndroidView(factory = { context ->
        View(context).apply {
            systemUiVisibility = WindowManager.LayoutParams.FLAG_FULLSCREEN
            val window = (context as Activity).window
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                val windowInsetsController = window.insetsController
                windowInsetsController?.let { controller ->
                    controller.hide(WindowInsets.Type.statusBars())
                    controller.systemBarsBehavior =
                        WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
            } else {
                @Suppress("DEPRECATION")
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            }
        }
    })
}

fun setStatusBarGradiant(
    activity: Activity,
    drawableId: Int,
    showStatusBar: Boolean = true,
    statusBarColor: Int = android.R.color.transparent,
    hideNavigationBar: Boolean = true,
    navigationBarColor: Int = android.R.color.transparent
) {
    val window: Window = activity.window
    val background = ContextCompat.getDrawable(activity, drawableId)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

    // Set status bar visibility and color
    if (showStatusBar) {
        window.statusBarColor = ContextCompat.getColor(activity, statusBarColor)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    } else {
        window.statusBarColor = ContextCompat.getColor(activity, android.R.color.transparent)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    // Set navigation bar visibility and color
    if (hideNavigationBar) {
        window.navigationBarColor = ContextCompat.getColor(activity, navigationBarColor)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN)
    } else {
        window.navigationBarColor = ContextCompat.getColor(activity, android.R.color.transparent)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }

    window.setBackgroundDrawable(background)
}






