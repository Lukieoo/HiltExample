package com.lukieoo.hiltexample.di

import android.content.Context
import androidx.room.Room
import com.lukieoo.hiltexample.room.CatDao
import com.lukieoo.hiltexample.room.CatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideCatDb(@ApplicationContext context: Context): CatDatabase {
        return Room
            .databaseBuilder(
                context,
                CatDatabase::class.java,
                CatDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCatDAO(catDatabase: CatDatabase): CatDao {
        return catDatabase.blogDao()
    }
}