package com.lukieoo.hiltexample.room

import com.lukieoo.hiltexample.util.EntityMapper
import com.lukieoo.hiltexample.Cat
import javax.inject.Inject

class CacheMapper
@Inject
constructor():
    EntityMapper<CatCacheEntity, Cat> {

    override fun mapFromEntity(entity: CatCacheEntity): Cat{
        return Cat(
            id = entity.id,
            url = entity.url,
            width = entity.width,
            height = entity.height
        )
    }

    override fun mapToEntity(domainModel: Cat): CatCacheEntity {
        return CatCacheEntity(
            id = domainModel.id,
            url = domainModel.url,
            width = domainModel.width,
            height = domainModel.height
        )
    }

    fun mapFromEntityList(entities: List<CatCacheEntity>): List<Cat>{
        return entities.map { mapFromEntity(it) }
    }
}











