package com.eatbee.presentation.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eatbee.presentation.common.BackPressState
import com.eatbee.presentation.ui.screen.main.EatBeeScreen
import com.eatbee.presentation.ui.screen.splash.SplashScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EatBeeNavHost() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var backPressState by remember { mutableStateOf<BackPressState>(BackPressState.Idle) }

    BackHandler(true) {
        if (navController.previousBackStackEntry != null) {
            navController.popBackStack()
            return@BackHandler
        }

        when (backPressState) {
            BackPressState.Idle -> {
                backPressState = BackPressState.WaitingConfirm

                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = "뒤로 가기를 한 번 더 누르면 종료됩니다.",
                    )
                }

                coroutineScope.launch {
                    delay(3500L)
                    backPressState = BackPressState.Idle
                }

            }

            BackPressState.WaitingConfirm -> {
                (navController.context as? Activity)?.finish()
            }
        }
    }

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