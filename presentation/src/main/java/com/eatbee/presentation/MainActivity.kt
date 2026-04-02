package com.eatbee.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.eatbee.presentation.ui.screen.EatBeeScreen
import com.eatbee.presentation.ui.theme.EatBeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            EatBeeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EatBeeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}