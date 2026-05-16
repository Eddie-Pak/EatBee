package com.eatbee.data.local.datasource.impl

import android.content.Context
import com.eatbee.data.local.datasource.EatBeeMatzipDataSource
import com.eatbee.data.model.EatBeeMatzipResponse
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class EatBeeMatzipDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val moshi: Moshi
) : EatBeeMatzipDataSource {
    override suspend fun getMatzipData(): EatBeeMatzipResponse {
        val jsonString = context.assets.open("eatbee_matzip.json").bufferedReader().use { it.readText() }

        return moshi.adapter(EatBeeMatzipResponse::class.java).fromJson(jsonString) ?: throw Exception("Failed to parse JSON")
    }
}