package com.eatbee.data.mapper

import com.eatbee.data.dto.EatBeeMatzipDto
import com.eatbee.data.local.entity.EatBeeMatzipEntity
import com.eatbee.domain.model.EatBeeMatzip
import kotlin.text.toDoubleOrNull

fun EatBeeMatzipDto.toEntity(): EatBeeMatzipEntity = EatBeeMatzipEntity(
    id = id,
    title = title,
    link = link,
    roadAddress = roadAddress,
    mapx = mapx.toDoubleOrNull() ?: 0.0,
    mapy = mapy.toDoubleOrNull() ?: 0.0
)

fun EatBeeMatzipEntity.toDomain(): EatBeeMatzip = EatBeeMatzip(
    id = id,
    title = title,
    link = link,
    roadAddress = roadAddress,
    mapx = mapx,
    mapy = mapy
)