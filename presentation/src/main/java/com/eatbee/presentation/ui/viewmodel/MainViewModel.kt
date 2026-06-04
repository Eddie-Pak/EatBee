package com.eatbee.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eatbee.domain.common.AppResult
import com.eatbee.domain.location.LocationTracker
import com.eatbee.domain.model.EatBeeMatzip
import com.eatbee.domain.usecase.GetAllMatzipUseCase
import com.eatbee.presentation.common.EatBeeMapEvent
import com.eatbee.presentation.common.EatBeeMapUiState
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllMatzipUseCase: GetAllMatzipUseCase,
    private val locationTracker: LocationTracker
) : ViewModel() {

    private val _currentLocation = MutableStateFlow<LatLng?>(null)

    val eatbeeMapUiState: StateFlow<EatBeeMapUiState> = combine(
        getAllMatzipUseCase(),
        _currentLocation
    ) { matzips, location ->
        EatBeeMapUiState(
            matzipList = matzips,
            currentLocation = location
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = EatBeeMapUiState()
    )

    private val _uiEvent = Channel<EatBeeMapEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun getCurrentLocation() {
        viewModelScope.launch {
            when (val result = locationTracker.getCurrentLocation()) {
                is AppResult.Success -> {
                    val latLng = LatLng(result.data.first, result.data.second)
                    _currentLocation.value = latLng
                    _uiEvent.send(EatBeeMapEvent.MoveCamera(latLng))
                }

                is AppResult.Error -> {
                    _uiEvent.send(EatBeeMapEvent.ShowSnackBar("현재위치를 표시할 수 없습니다. 잠시 후 시도해주세요."))
                }

                else -> {}
            }
        }
    }

    fun onMatzipClick(matzip: EatBeeMatzip) {
        viewModelScope.launch {
            val targetLocation = LatLng(matzip.mapy, matzip.mapx)

            _uiEvent.send(EatBeeMapEvent.MoveCamera(targetLocation))
        }
    }
}