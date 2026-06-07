package com.eatbee.data.local.datasource.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import com.eatbee.data.local.datasource.PreferenceDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferenceDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PreferenceDataSource {
    private object PreferencesKeys {
        val DB_VERSION = doublePreferencesKey("db_version")
    }

    override val dbVersion: Flow<Double> = dataStore.data.map { preferences ->
        preferences[PreferencesKeys.DB_VERSION] ?: 0.0
    }

    override suspend fun setDbVersion(version: Double) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.DB_VERSION] = version
        }
    }
}