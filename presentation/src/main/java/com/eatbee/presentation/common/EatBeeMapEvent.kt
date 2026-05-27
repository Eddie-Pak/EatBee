package com.eatbee.presentation.common

import com.naver.maps.geometry.LatLng

sealed class EatBeeMapEvent {
    data class ShowSnackBar(val message: String) : EatBeeMapEvent()
    data class MoveCamera(val location: LatLng) : EatBeeMapEvent()
}