package com.example.filler.gui

import android.app.Activity
import android.view.View
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

fun hideNavBar(activity: Activity) {
    val window: Window = activity.window
    val decorView: View = activity.window.decorView

    WindowCompat.setDecorFitsSystemWindows(window, false)
    val controllerCompat = WindowInsetsControllerCompat(window, decorView)
    controllerCompat.hide(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.navigationBars())
    controllerCompat.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
}