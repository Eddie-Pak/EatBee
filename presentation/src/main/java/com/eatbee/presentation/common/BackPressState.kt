package com.eatbee.presentation.common

sealed interface BackPressState {
    data object Idle : BackPressState        // 아무것도 안 누른 상태
    data object WaitingConfirm : BackPressState
}