package com.eatbee.data.local.datasource

import com.eatbee.data.model.EatBeeMatzipResponse

interface EatBeeMatzipDataSource {
    suspend fun getMatzipData(): EatBeeMatzipResponse
}