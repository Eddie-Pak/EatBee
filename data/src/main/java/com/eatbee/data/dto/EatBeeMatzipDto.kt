package com.eatbee.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EatBeeMatzipDto(
    val id: Int,
    val title: String,
    val link: String,
    val roadAddress: String,
    val mapx: String,
    val mapy: String
)
