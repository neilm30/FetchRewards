package com.fetchrewards.data.repositoryimpl

import com.fetchrewards.data.remote.apis.FetchRewardsApis
import com.fetchrewards.data.remote.dtotoentity.toEntityList
import com.fetchrewards.domain.common.NetworkResult
import com.fetchrewards.domain.entity.FetchRewardsEntity
import com.fetchrewards.domain.repositories.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DataRepositoryImpl(private val api: FetchRewardsApis) :
    BaseRepositoryImpl(),
    DataRepository {
    override fun fetchRewardDetails(): Flow<NetworkResult<List<FetchRewardsEntity>>> =
        flow {
            emit(
                apiCall {
                    api.fetchData().toEntityList()
                },
            )
        }.flowOn(Dispatchers.IO)
}
