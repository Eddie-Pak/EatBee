package com.eatbee.data.repository

import android.util.Log.e
import androidx.room.withTransaction
import com.eatbee.data.local.dao.EatBeeMatzipDao
import com.eatbee.data.local.database.EatBeeMatzipDatabase
import com.eatbee.data.local.datasource.EatBeeMatzipDataSource
import com.eatbee.data.local.datasource.PreferenceDataSource
import com.eatbee.data.mapper.toDomain
import com.eatbee.data.mapper.toEntity
import com.eatbee.domain.common.AppResult
import com.eatbee.domain.common.toAppResult
import com.eatbee.domain.model.EatBeeMatzip
import com.eatbee.domain.repository.EatBeeMatzipRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EatBeeMatzipRepositoryImpl @Inject constructor(
    private val database: EatBeeMatzipDatabase,
    private val eatBeeMatzipDao: EatBeeMatzipDao,
    private val preferenceDataSource: PreferenceDataSource,
    private val eatBeeMatzipDataSource: EatBeeMatzipDataSource
) : EatBeeMatzipRepository {
    override suspend fun initializeMatzipData(): AppResult<Unit> = runCatching {
        val response = eatBeeMatzipDataSource.getMatzipData()

        val currentVersion = preferenceDataSource.dbVersion.first()

        if (currentVersion < response.version) {
            val entityList = response.items.map { it.toEntity() }

            database.withTransaction {
                eatBeeMatzipDao.deleteAllMatzip()
                eatBeeMatzipDao.insertAllMatzip(entityList)
            }

            preferenceDataSource.setDbVersion(response.version)
        }
    }.toAppResult()

    override fun getAllMatzip(): Flow<List<EatBeeMatzip>> {
        return eatBeeMatzipDao.getAllMatzip().map { entityList ->
            entityList.map { it.toDomain() }
        }
    }
}