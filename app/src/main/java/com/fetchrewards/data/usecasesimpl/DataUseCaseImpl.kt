package com.fetchrewards.data.usecasesimpl

import com.fetchrewards.domain.common.NetworkResult
import com.fetchrewards.domain.entity.FetchRewardsEntity
import com.fetchrewards.domain.repositories.DataRepository
import com.fetchrewards.domain.usecases.DataRepositoryUseCase
import kotlinx.coroutines.flow.Flow

class DataUseCaseImpl(private val repository: DataRepository) :
    DataRepositoryUseCase {
    override fun fetchRewardDetails(): Flow<NetworkResult<List<FetchRewardsEntity>>> {
        return repository.fetchRewardDetails()
    }
}
