package com.fetchrewards.presentation.intent

sealed class DataIntent {
    data object GetRewardsList : DataIntent()
}
