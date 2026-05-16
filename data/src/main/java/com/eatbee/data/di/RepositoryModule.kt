package com.eatbee.data.di

import com.eatbee.data.repository.EatBeeMatzipRepositoryImpl
import com.eatbee.domain.repository.EatBeeMatzipRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindEatBeeMatzipRepository(
        eatBeeMatzipRepositoryImpl: EatBeeMatzipRepositoryImpl
    ): EatBeeMatzipRepository

}