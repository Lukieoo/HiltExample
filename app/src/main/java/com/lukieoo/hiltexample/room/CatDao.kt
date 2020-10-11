package com.lukieoo.hiltexample.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(catEntity: CatCacheEntity): Long

    @Query("SELECT * FROM blogs")
    suspend fun get(): List<CatCacheEntity>
}