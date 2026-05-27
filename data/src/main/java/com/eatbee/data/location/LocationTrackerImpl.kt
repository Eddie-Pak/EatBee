package com.eatbee.data.location

import android.annotation.SuppressLint
import com.eatbee.domain.common.AppResult
import com.eatbee.domain.common.toAppResult
import com.eatbee.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LocationTrackerImpl @Inject constructor(
    private val fusedLocationClient: FusedLocationProviderClient
) : LocationTracker {

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): AppResult<Pair<Double, Double>> = runCatching {
        val location = fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            CancellationTokenSource().token
        ).await()

        location?.let { Pair(it.latitude, it.longitude) } ?: throw IllegalStateException("Location is null")
    }.toAppResult()
}