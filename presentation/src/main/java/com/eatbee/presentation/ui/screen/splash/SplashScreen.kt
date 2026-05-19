package com.eatbee.presentation.ui.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.eatbee.presentation.R
import com.eatbee.presentation.common.SplashEvent
import com.eatbee.presentation.ui.theme.EatBeeTheme
import com.eatbee.presentation.ui.viewmodel.SplashViewModel

@Composable
fun SplashScreen(
    modifier: Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    onShowError: (String) -> Unit,
    goMain: () -> Unit,
) {
    LaunchedEffect(viewModel.splashEvent) {
        viewModel.splashEvent.collect { event ->
            when (event) {
                is SplashEvent.NavigateToMain -> goMain()
                is SplashEvent.ShowError -> {
                    onShowError(event.message)
                    goMain()
                }
            }
        }
    }

    EatBeeTheme {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = R.drawable.image_eatbee_logo,
                    modifier = Modifier.size(150.dp),
                    contentDescription = "EatBee Logo"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "꿀벌들의 8자 춤을 확인하세요.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}