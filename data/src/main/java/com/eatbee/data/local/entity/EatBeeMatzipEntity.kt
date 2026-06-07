package com.eatbee.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EatBeeMatzip")
data class EatBeeMatzipEntity(
    @PrimaryKey val id : Int,
    val title: String,
    val link: String,
    val roadAddress: String,
    val mapx: Double,
    val mapy: Double
)
