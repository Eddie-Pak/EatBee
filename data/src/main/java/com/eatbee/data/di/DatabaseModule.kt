package com.eatbee.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.eatbee.data.local.dao.EatBeeMatzipDao
import com.eatbee.data.local.database.EatBeeMatzipDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideEatBeeMatzipDatabase(
        @ApplicationContext context: Context,
    ): EatBeeMatzipDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            EatBeeMatzipDatabase::class.java,
            "EatBeeMatzip.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideEatBeeMatzipDao(
        database: EatBeeMatzipDatabase,
    ): EatBeeMatzipDao {
        return database.eatBeeMatzipDao()
    }

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("eatbee_preference") }
        )
    }

}