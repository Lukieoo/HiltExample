package com.lukieoo.hiltexample.retrofit

import com.lukieoo.hiltexample.util.EntityMapper
import com.lukieoo.hiltexample.model.Cat
import javax.inject.Inject

class NetworkMapper
@Inject
constructor():
    EntityMapper<CatNetworkEntity, Cat> {

    override fun mapFromEntity(entity: CatNetworkEntity): Cat {
        return Cat(
            id = entity.id,
            url = entity.url,
            width = entity.width,
            height = entity.height
        )
    }

    override fun mapToEntity(domainModel: Cat): CatNetworkEntity {
        return CatNetworkEntity(
            id = domainModel.id,
            url = domainModel.url,
            width = domainModel.width,
            height = domainModel.height
        )
    }


    fun mapFromEntityList(entities: List<CatNetworkEntity>): List<Cat>{
        return entities.map { mapFromEntity(it) }
    }

}





















