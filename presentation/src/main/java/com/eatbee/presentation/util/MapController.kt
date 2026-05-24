package com.eatbee.presentation.util

import com.eatbee.domain.model.EatBeeMatzip
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker

class MapController {
    private var naverMap: NaverMap? = null

    private val activeMarkers = mutableListOf<Marker>()

    fun setMap(naverMap: NaverMap, matzipList: List<EatBeeMatzip>) {
        this.naverMap = naverMap
        setupMapSettings(naverMap)
        updateMarkers(matzipList)
    }

    private fun setupMapSettings(map: NaverMap) {
        map.uiSettings.apply {
            isCompassEnabled = true
            isScaleBarEnabled = true
            isLocationButtonEnabled = false
        }

        map.minZoom = 5.5

        val initialPosition = LatLng(37.5666805, 126.9784147)
        map.moveCamera(CameraUpdate.scrollTo(initialPosition).animate(CameraAnimation.Easing))
    }

    fun updateMarkers(matzipList: List<EatBeeMatzip>) {
        val map = naverMap ?: return

        clearMarkers()

        matzipList.forEach { matzip ->
            val marker = Marker().apply {
                position = LatLng(matzip.mapy, matzip.mapx)
                captionText = matzip.title
                this.map = map

                setOnClickListener {
                    true
                }
            }

            activeMarkers.add(marker)
        }
    }

    fun clearMarkers() {
        activeMarkers.forEach { it.map = null }
        activeMarkers.clear()
    }
}