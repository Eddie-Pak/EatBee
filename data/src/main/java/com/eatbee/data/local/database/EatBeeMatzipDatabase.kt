package com.eatbee.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eatbee.data.local.dao.EatBeeMatzipDao
import com.eatbee.data.local.entity.EatBeeMatzipEntity

@Database(
    entities = [EatBeeMatzipEntity::class],
    version = 1,
    exportSchema = false
)
abstract class EatBeeMatzipDatabase : RoomDatabase() {
    abstract fun eatBeeMatzipDao(): EatBeeMatzipDao
}