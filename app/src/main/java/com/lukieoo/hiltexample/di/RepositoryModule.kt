package com.lukieoo.hiltexample.di

import com.lukieoo.hiltexample.repository.MainRepository
import com.lukieoo.hiltexample.retrofit.CatRetrofit
import com.lukieoo.hiltexample.retrofit.NetworkMapper
import com.lukieoo.hiltexample.room.CatDao
import com.lukieoo.hiltexample.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        catDao: CatDao,
        retrofit: CatRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository{
        return MainRepository(catDao, retrofit, cacheMapper, networkMapper)
    }
}














