package com.eatbee.presentation.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EatBeeScreen(modifier: Modifier) {
    Text(
        text = "Hello EatBee",
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier.padding(16.dp)
    )
}