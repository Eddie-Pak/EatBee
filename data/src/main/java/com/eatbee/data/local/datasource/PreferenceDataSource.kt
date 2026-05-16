package com.eatbee.data.local.datasource

import kotlinx.coroutines.flow.Flow

interface PreferenceDataSource {
    val dbVersion: Flow<Double>

    suspend fun setDbVersion(version: Double)
}