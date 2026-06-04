package com.eatbee.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.viewinterop.AndroidView
import com.eatbee.domain.model.EatBeeMatzip
import com.eatbee.presentation.ui.theme.EatBeeDimens
import com.eatbee.presentation.util.MapController
import com.eatbee.presentation.util.rememberMapViewWithLifecycle
import com.naver.maps.geometry.LatLng

@Composable
fun EatBeeNaverMap(
    modifier: Modifier,
    mapController: MapController,
    matzipList: List<EatBeeMatzip>,
    currentLocation: LatLng?,
    paddingValues: PaddingValues,
    onMarkerClick: (EatBeeMatzip) -> Unit,
    onCurrentLocationClick: () -> Unit,
) {
    val mapView = rememberMapViewWithLifecycle()

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
                        mapController.setMap(naverMap, matzipList) {
                            onMarkerClick(it)
                        }

                        currentLocation?.let { location ->
                            mapController.updateCurrentLocation(location)
                        }
                    }
                }
            },
            update = { view ->
                view.getMapAsync { naverMap ->
                    naverMap.setContentPadding(0, 0, 0, peekHeightPx)
                }
            }
        )

        MyLocationButton(
            Modifier.align(Alignment.BottomEnd)
                .padding(
                    bottom = paddingValues.calculateBottomPadding() + EatBeeDimens.Padding.XLarge,
                    end = EatBeeDimens.Padding.Medium
                )
        ) {
            onCurrentLocationClick()
        }
    }
}