package com.eatbee.presentation.common

sealed class EatBeeEvent {
    data class ShowSnackBar(val message: String) : EatBeeEvent()
}