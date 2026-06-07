package com.eatbee.presentation.ui.screen.main

import androidx.activity.compose.BackHandler
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eatbee.presentation.common.EatBeeMapEvent
import com.eatbee.presentation.ui.component.EatBeeNaverMap
import com.eatbee.presentation.ui.screen.main.bottomsheet.EatBeeBottomSheetScaffold
import com.eatbee.presentation.ui.screen.main.bottomsheet.MatzipDetailContent
import com.eatbee.presentation.ui.screen.main.bottomsheet.MatzipListContent
import com.eatbee.presentation.ui.theme.EatBeeTheme
import com.eatbee.presentation.ui.viewmodel.MainViewModel
import com.eatbee.presentation.util.AppPermissions
import com.eatbee.presentation.util.MapController
import com.eatbee.presentation.util.SystemBarsUtils
import com.eatbee.presentation.util.checkLocationPermission
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EatBeeScreen(
    modifier: Modifier,
    coroutineScope: CoroutineScope,
    showSnackBar: (String) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val eatbeeMapUiState by viewModel.eatbeeMapUiState.collectAsStateWithLifecycle()

    val mapController = remember { MapController() }

    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded
    )

    if (eatbeeMapUiState.selectedMatzip == null && sheetState.currentValue == SheetValue.PartiallyExpanded) {
        SystemBarsUtils(false)
    } else {
        SystemBarsUtils()
    }

    BackHandler(
        eatbeeMapUiState.selectedMatzip != null
                || sheetState.currentValue == SheetValue.Expanded
    ) {
        when {
            eatbeeMapUiState.selectedMatzip != null -> {
                viewModel.onBackFromDetail()
            }

            sheetState.currentValue == SheetValue.Expanded -> {
                coroutineScope.launch { sheetState.partialExpand() }
            }
        }
    }

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

                is EatBeeMapEvent.ShrinkBottomSheet -> {
                    coroutineScope.launch { sheetState.partialExpand() }
                }

                is EatBeeMapEvent.ExpandBottomSheet -> {
                    coroutineScope.launch { sheetState.expand() }
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
            sheetState = sheetState,
            sheetContent = {
                when (val selectedMatzip = eatbeeMapUiState.selectedMatzip) {
                    null -> {
                        MatzipListContent(
                            matzipList = eatbeeMapUiState.matzipList,
                        ) { matzip ->
                            viewModel.onMatzipClick(matzip)
                        }
                    }

                    else -> {
                        MatzipDetailContent(
                            matzip = selectedMatzip,
                        ) {
                            viewModel.onBackFromDetail()
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