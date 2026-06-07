package com.eatbee.data.di

import com.eatbee.data.local.datasource.EatBeeMatzipDataSource
import com.eatbee.data.local.datasource.PreferenceDataSource
import com.eatbee.data.local.datasource.impl.EatBeeMatzipDataSourceImpl
import com.eatbee.data.local.datasource.impl.PreferenceDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindPreferenceDataSource(
        preferenceDataSourceImpl: PreferenceDataSourceImpl
    ): PreferenceDataSource

    @Binds
    @Singleton
    abstract fun bindEatBeeMatzipDataSource(
        eatBeeMatzipDataSourceImpl: EatBeeMatzipDataSourceImpl
    ): EatBeeMatzipDataSource
}