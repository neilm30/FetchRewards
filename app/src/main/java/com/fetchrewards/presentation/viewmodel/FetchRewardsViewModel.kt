package com.fetchrewards.presentation.viewmodel

import com.fetchrewards.domain.common.NetworkResult
import com.fetchrewards.domain.entity.FetchRewardsEntity
import com.fetchrewards.domain.usecases.DataRepositoryUseCase
import com.fetchrewards.presentation.intent.DataIntent
import com.fetchrewards.presentation.state.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FetchRewardsViewModel(private val useCase: DataRepositoryUseCase) : BaseViewModel() {
    private val _dataState = MutableStateFlow<DataState>(DataState.Inactive)
    val dataState: StateFlow<DataState> =
        _dataState.asStateFlow() // use for compose ui else use stateflow for xml

    fun onEventChange(event: DataIntent) {
        when (event) {
            is DataIntent.GetRewardsList -> {
                loadRewardList()
            }
        }
    }

    private fun loadRewardList() {
        _dataState.value = DataState.Loading(loading = true)

        useCase.fetchRewardDetails().track {
            _dataState.value = DataState.Loading(loading = false)
            _dataState.value =
                when (it) {
                    is NetworkResult.Success -> {
                        DataState.Success(getFilteredSortedData(it.data))
                    }

                    is NetworkResult.Error -> DataState.Error(Pair(it.error?.errorCode, it.error?.errorMessage))
                    is NetworkResult.Exception -> TODO()
                }
        }
    }

    private fun getFilteredSortedData(data: List<FetchRewardsEntity>) = data.sortedWith(compareBy({ it.listId }, { it.name }))
}
