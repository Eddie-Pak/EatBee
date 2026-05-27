package com.eatbee.domain.location

import com.eatbee.domain.common.AppResult

interface LocationTracker {
    suspend fun getCurrentLocation(): AppResult<Pair<Double, Double>>
}