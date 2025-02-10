package com.fetchrewards.presentation.state

import com.fetchrewards.domain.entity.FetchRewardsEntity

sealed class DataState {
    data object Inactive : DataState()

    data class Loading(val loading: Boolean = false) : DataState()

    class Success(val data: List<FetchRewardsEntity>) : DataState()

    class Error(val error: Pair<String?, String?>) : DataState()
}
