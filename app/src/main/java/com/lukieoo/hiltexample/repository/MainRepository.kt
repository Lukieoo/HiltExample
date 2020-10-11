package com.lukieoo.hiltexample.repository


import com.lukieoo.hiltexample.util.DataState
import com.lukieoo.hiltexample.retrofit.CatRetrofit
import com.lukieoo.hiltexample.retrofit.NetworkMapper
import com.lukieoo.hiltexample.room.CacheMapper
import com.lukieoo.hiltexample.room.CatDao
import com.lukieoo.hiltexample.Cat
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository
constructor(
    private val catDao: CatDao,
    private val catRetrofit: CatRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){
    suspend fun getCats(): Flow<DataState<List<Cat>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try{
            val networkBlogs = catRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for(blog in blogs){
                catDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = catDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}