package com.fetchrewards.domain.repositories

import com.fetchrewards.domain.common.NetworkResult
import com.fetchrewards.domain.entity.FetchRewardsEntity
import com.githubrepo.domain.repositories.BaseRepository
import kotlinx.coroutines.flow.Flow

interface DataRepository : BaseRepository {
    fun fetchRewardDetails(): Flow<NetworkResult<List<FetchRewardsEntity>>>
}
