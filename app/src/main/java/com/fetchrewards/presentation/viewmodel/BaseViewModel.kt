package com.fetchrewards.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fetchrewards.domain.common.NetworkResult
import com.fetchrewards.domain.entity.ErrorEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    /**
     * Extension function for calling api using flow.
     */
    protected fun <T : Any> Flow<T>.track(action: (value: T) -> Unit) =
        viewModelScope.launch {
            collect {
                action(it)
            }
        }
}
