package com.eatbee.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.eatbee.presentation.navigation.EatBeeNavHost
import com.eatbee.presentation.ui.theme.EatBeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            EatBeeTheme {
                EatBeeNavHost()
            }
        }
    }
}