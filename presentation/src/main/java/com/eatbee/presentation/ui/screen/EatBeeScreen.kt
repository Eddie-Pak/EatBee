package com.eatbee.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eatbee.presentation.common.EatBeeMapEvent
import com.eatbee.presentation.ui.component.MatzipCard
import com.eatbee.presentation.ui.component.MyLocationButton
import com.eatbee.presentation.ui.theme.EatBeeDimens
import com.eatbee.presentation.ui.theme.EatBeeTheme
import com.eatbee.presentation.ui.viewmodel.MainViewModel
import com.eatbee.presentation.util.AppPermissions
import com.eatbee.presentation.util.MapController
import com.eatbee.presentation.util.SystemBarsUtils
import com.eatbee.presentation.util.checkLocationPermission
import com.eatbee.presentation.util.rememberMapViewWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EatBeeScreen(
    modifier: Modifier,
    showSnackBar: (String) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {

    SystemBarsUtils()

    val mapView = rememberMapViewWithLifecycle()

    val eatbeeMapUiState by viewModel.eatbeeMapUiState.collectAsStateWithLifecycle()

    val mapController = remember { MapController() }

    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded
    )

    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    val locationPermissionLauncher = checkLocationPermission(
        onGranted = {
            viewModel.getCurrentLocation()
        },
        onDenied = {
            showSnackBar("위치권한이 없어 현재위치를 표시할 수 없습니다.")
        }
    )

    LaunchedEffect(Unit) {
        locationPermissionLauncher.launch(AppPermissions.location)
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is EatBeeMapEvent.ShowSnackBar -> {
                    showSnackBar(event.message)
                }
                is EatBeeMapEvent.MoveCamera -> {
                    mapController.moveCamera(event.location)
                }
            }
        }
    }

    LaunchedEffect(eatbeeMapUiState.matzipList) {
        mapController.updateMarkers(eatbeeMapUiState.matzipList) {
            viewModel.onMatzipClick(it)
        }
    }

    LaunchedEffect(eatbeeMapUiState.currentLocation) {
        eatbeeMapUiState.currentLocation?.let { location ->
            mapController.updateCurrentLocation(location)
        }
    }

    EatBeeTheme {
        BottomSheetScaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState,
            sheetPeekHeight = 150.dp,
            sheetContainerColor = MaterialTheme.colorScheme.surface,
            sheetContent = {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(0.7f),
                    contentPadding = PaddingValues(bottom = EatBeeDimens.Padding.Small)
                ) {
                    items(
                        items = eatbeeMapUiState.matzipList,
                        key = { it.id }
                    ) { matzip ->
                        MatzipCard(matzip) {
                            viewModel.onMatzipClick(it)
                        }
                    }
                }
            }
        ) { paddingValues ->

            val density = LocalDensity.current
            val peekHeightPx = with(density) { paddingValues.calculateBottomPadding().roundToPx() }

            Box(
                modifier = modifier.fillMaxSize()
            ) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = {
                        mapView.apply {
                            getMapAsync { naverMap ->
                                naverMap.setContentPadding(0, 0, 0, peekHeightPx)

                                mapController.setMap(naverMap, eatbeeMapUiState.matzipList) {
                                    viewModel.onMatzipClick(it)
                                }

                                eatbeeMapUiState.currentLocation?.let { location ->
                                    mapController.updateCurrentLocation(location)
                                }
                            }
                        }
                    },
                )

                MyLocationButton(
                    Modifier.align(Alignment.BottomEnd)
                        .padding(
                            bottom = paddingValues.calculateBottomPadding() + EatBeeDimens.Padding.XLarge,
                            end = EatBeeDimens.Padding.Medium
                        )
                ) {
                    locationPermissionLauncher.launch(AppPermissions.location)
                }
            }
        }
    }
}