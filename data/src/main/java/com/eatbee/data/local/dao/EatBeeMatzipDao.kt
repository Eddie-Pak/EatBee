package com.eatbee.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eatbee.data.local.entity.EatBeeMatzipEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EatBeeMatzipDao {
    @Query("SELECT * FROM EatBeeMatzip")
    fun getAllMatzip(): Flow<List<EatBeeMatzipEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMatzip(matzipList: List<EatBeeMatzipEntity>)

    @Query("DELETE FROM EatBeeMatzip")
    suspend fun deleteAllMatzip()
}