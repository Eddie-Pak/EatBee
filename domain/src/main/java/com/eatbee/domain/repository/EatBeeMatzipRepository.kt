package com.eatbee.domain.repository

import com.eatbee.domain.common.AppResult
import com.eatbee.domain.model.EatBeeMatzip
import kotlinx.coroutines.flow.Flow

interface EatBeeMatzipRepository {
    suspend fun initializeMatzipData() : AppResult<Unit>
    fun getAllMatzip() : Flow<List<EatBeeMatzip>>
}