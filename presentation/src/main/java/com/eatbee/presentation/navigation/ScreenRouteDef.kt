package com.eatbee.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenRouteDef {
    @Serializable
    data object Splash : ScreenRouteDef()

    @Serializable
    data object Main : ScreenRouteDef()
}