package com.eatbee.presentation.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eatbee.presentation.ui.theme.EatBeeTheme
import com.eatbee.presentation.ui.viewmodel.MainViewModel
import com.eatbee.presentation.util.MapController
import com.eatbee.presentation.util.SystemBarsUtils
import com.eatbee.presentation.util.rememberMapViewWithLifecycle

@Composable
fun EatBeeScreen(
    modifier: Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {

    SystemBarsUtils()

    val mapView = rememberMapViewWithLifecycle()

    val eatbeeMatzipList by viewModel.matzipList.collectAsStateWithLifecycle()

    val mapController = remember { MapController() }

    LaunchedEffect(eatbeeMatzipList) {
        mapController.updateMarkers(eatbeeMatzipList)
    }

    EatBeeTheme {
        AndroidView(
            modifier = modifier.fillMaxSize(),
            factory = {
                mapView.apply {
                    getMapAsync { naverMap ->
                        mapController.setMap(naverMap, eatbeeMatzipList)
                    }
                }
            },
            update = { view ->

            }
        )
    }
}