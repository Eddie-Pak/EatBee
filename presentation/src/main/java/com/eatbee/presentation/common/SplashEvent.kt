package com.eatbee.presentation.common

sealed interface SplashEvent {
    data object NavigateToMain : SplashEvent
    data class ShowError(val message: String) : SplashEvent
}