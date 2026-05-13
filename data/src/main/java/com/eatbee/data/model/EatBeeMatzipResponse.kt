package com.eatbee.data.model

import com.eatbee.data.dto.EatBeeMatzipDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EatBeeMatzipResponse(
    val total: Int,
    val version: Double,
    val items: List<EatBeeMatzipDto>
)
