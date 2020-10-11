package com.lukieoo.hiltexample.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CatCacheEntity::class ], version = 1)
abstract class CatDatabase: RoomDatabase() {

    abstract fun blogDao(): CatDao

    companion object{
        val DATABASE_NAME: String = "blog_db"
    }


}