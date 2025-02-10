package com.fetchrewards.domain.usecases

import com.fetchrewards.domain.common.NetworkResult
import com.fetchrewards.domain.entity.FetchRewardsEntity
import kotlinx.coroutines.flow.Flow

interface DataRepositoryUseCase {
    fun fetchRewardDetails(): Flow<NetworkResult<List<FetchRewardsEntity>>>
}
