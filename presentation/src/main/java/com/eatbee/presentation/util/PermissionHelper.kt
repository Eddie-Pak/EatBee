package com.eatbee.presentation.util

import android.Manifest
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

object AppPermissions {
    val location = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
}

@Composable
fun checkLocationPermission(
    onGranted: () -> Unit,
    onDenied: () -> Unit = {},
): ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>> {
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
    ) { permissions ->
        val isGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if (isGranted) {
            onGranted()
        } else {
            onDenied()
        }
    }
}
