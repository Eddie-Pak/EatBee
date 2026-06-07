package com.eatbee.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eatbee.presentation.ui.screen.main.EatBeeScreen
import com.eatbee.presentation.ui.screen.splash.SplashScreen
import kotlinx.coroutines.launch

@Composable
fun EatBeeNavHost() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = ScreenRouteDef.Splash
        ) {
            composable<ScreenRouteDef.Splash> {
                SplashScreen(
                    modifier = Modifier.padding(paddingValues),
                    onShowError = { errorMessage ->
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(errorMessage)
                        }
                    }
                ) {
                    navController.navigate(ScreenRouteDef.Main) {
                        popUpTo(ScreenRouteDef.Splash) { inclusive = true }
                    }
                }
            }

            composable<ScreenRouteDef.Main> {
                EatBeeScreen(
                    modifier = Modifier.padding(paddingValues),
                    coroutineScope = coroutineScope,
                    showSnackBar = { message ->
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(message)
                        }
                    }
                )
            }
        }
    }
}