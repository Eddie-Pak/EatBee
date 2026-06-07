package com.eatbee.presentation.common

import com.eatbee.domain.model.EatBeeMatzip
import com.naver.maps.geometry.LatLng

data class EatBeeMapUiState(
    val matzipList: List<EatBeeMatzip> = emptyList(),
    val currentLocation: LatLng? = null,
    val selectedMatzip: EatBeeMatzip? = null
)
