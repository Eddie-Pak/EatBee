package com.eatbee.presentation.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eatbee.presentation.common.EatBeeMapEvent
import com.eatbee.presentation.ui.component.EatBeeBottomSheetScaffold
import com.eatbee.presentation.ui.component.EatBeeNaverMap
import com.eatbee.presentation.ui.component.MatzipCard
import com.eatbee.presentation.ui.theme.EatBeeDimens
import com.eatbee.presentation.ui.theme.EatBeeTheme
import com.eatbee.presentation.ui.viewmodel.MainViewModel
import com.eatbee.presentation.util.AppPermissions
import com.eatbee.presentation.util.MapController
import com.eatbee.presentation.util.SystemBarsUtils
import com.eatbee.presentation.util.checkLocationPermission
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EatBeeScreen(
    modifier: Modifier,
    showSnackBar: (String) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {

    SystemBarsUtils()

    val eatbeeMapUiState by viewModel.eatbeeMapUiState.collectAsStateWithLifecycle()

    val mapController = remember { MapController() }

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
        EatBeeBottomSheetScaffold(
            sheetContent = {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(0.7f),
                    contentPadding = PaddingValues(bottom = EatBeeDimens.Padding.Small)
                ) {
                    items(
                        items = eatbeeMapUiState.matzipList,
                        key = { it.id }
                    ) { matzip ->
                        MatzipCard(matzip) { matzip ->
                            viewModel.onMatzipClick(matzip)
                        }
                    }
                }
            }
        ) { paddingValues ->
            EatBeeNaverMap(
                modifier = modifier,
                mapController = mapController,
                matzipList = eatbeeMapUiState.matzipList,
                currentLocation = eatbeeMapUiState.currentLocation,
                paddingValues = paddingValues,
                onMarkerClick = { matzip ->
                    viewModel.onMatzipClick(matzip)
                },
                onCurrentLocationClick = {
                    locationPermissionLauncher.launch(AppPermissions.location)
                }
            )
        }
    }
}