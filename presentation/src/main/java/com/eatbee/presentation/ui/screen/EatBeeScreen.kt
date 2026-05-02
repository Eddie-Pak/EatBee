package com.eatbee.presentation.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.eatbee.presentation.ui.theme.EatBeeTheme
import com.eatbee.presentation.util.SystemBarsUtils
import com.eatbee.presentation.util.rememberMapViewWithLifecycle
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap

@Composable
fun EatBeeScreen(modifier: Modifier) {

    SystemBarsUtils()

    val mapView = rememberMapViewWithLifecycle()

    EatBeeTheme {
        AndroidView(
            modifier = modifier.fillMaxSize(),
            factory = {
                mapView.apply {
                    getMapAsync { naverMap ->
                        setupMap(naverMap)
                    }
                }
            },
            update = { view ->

            }
        )
    }
}

private fun setupMap(naverMap: NaverMap) {
    naverMap.uiSettings.apply {
        isCompassEnabled = true
        isScaleBarEnabled = true
        isLocationButtonEnabled = false
    }

    naverMap.minZoom = 5.5

    val initialPosition = LatLng(37.5666805, 126.9784147)
    naverMap.moveCamera(CameraUpdate.scrollTo(initialPosition).animate(CameraAnimation.Easing))
}