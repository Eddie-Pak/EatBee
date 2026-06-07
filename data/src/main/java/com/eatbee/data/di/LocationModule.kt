package com.eatbee.data.di

import android.content.Context
import com.eatbee.data.location.LocationTrackerImpl
import com.eatbee.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(
        @ApplicationContext context: Context
    ): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    @Singleton
    fun provideLocationTracker(
        fusedLocationProviderClient: FusedLocationProviderClient
    ): LocationTracker {
        return LocationTrackerImpl(fusedLocationProviderClient)
    }
}