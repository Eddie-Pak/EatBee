package com.eatbee.presentation.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.eatbee.presentation.R
import com.eatbee.presentation.ui.theme.EatBeeDimens

@Composable
fun MyLocationButton(
    modifier: Modifier,
    onClick: () -> Unit = { }
) {
    FloatingActionButton(
        modifier = modifier.size(EatBeeDimens.Size.ButtonMedium),
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_my_location),
            contentDescription = "My Location"
        )
    }
}