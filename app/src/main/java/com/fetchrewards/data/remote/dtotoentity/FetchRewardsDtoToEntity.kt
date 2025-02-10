package com.fetchrewards.data.remote.dtotoentity

import com.fetchrewards.data.remote.dto.FetchRewardsDto
import com.fetchrewards.domain.entity.FetchRewardsEntity

fun FetchRewardsDto.toEntity() =
    FetchRewardsEntity(
        id = this.id,
        listId = this.listId,
        name = this.name,
    )

/**
 * Extension function to map a list of [FetchRewardsDto] objects to a list of [FetchRewardsEntity] objects.
 */
fun List<FetchRewardsDto>.toEntityList(): List<FetchRewardsEntity> =
    filter { it.hasValidData() }.map {
        it.toEntity()
    }
