package com.eatbee.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eatbee.domain.common.AppResult
import com.eatbee.domain.usecase.InitializeMatzipDataUseCase
import com.eatbee.presentation.common.SplashEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val initializeMatzipDataUseCase: InitializeMatzipDataUseCase
) : ViewModel() {
    private val _splashEvent = Channel<SplashEvent>()
    val splashEvent = _splashEvent.receiveAsFlow()

    init {
        initializeEatBeeMatzipData()
    }

    fun initializeEatBeeMatzipData() {
        viewModelScope.launch {
            when (val result = initializeMatzipDataUseCase()) {
                is AppResult.Success -> {
                    _splashEvent.send(SplashEvent.NavigateToMain)
                }
                is AppResult.Error -> {
                    _splashEvent.send(SplashEvent.ShowError(result.exception.message.toString()))
                }
                else -> {}
            }
        }
    }
}