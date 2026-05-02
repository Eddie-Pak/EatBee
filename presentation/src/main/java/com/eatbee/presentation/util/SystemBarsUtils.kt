package com.eatbee.presentation.util

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun SystemBarsUtils(
    hideNavigationBar: Boolean = true,
) {
    val context = LocalContext.current
    val window = (context as? Activity)?.window ?: return

    DisposableEffect(hideNavigationBar) {
        val controller = WindowCompat.getInsetsController(window, window.decorView)

        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        if (hideNavigationBar) {
            controller.hide(WindowInsetsCompat.Type.navigationBars())
        } else {
            controller.show(WindowInsetsCompat.Type.navigationBars())
        }

        onDispose {
            controller.show(WindowInsetsCompat.Type.navigationBars())
        }
    }
}