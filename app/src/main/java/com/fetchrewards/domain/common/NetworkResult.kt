package com.fetchrewards.domain.common

import com.fetchrewards.domain.entity.ErrorEntity

sealed class NetworkResult<T : Any> {
    class Success<T : Any>(val data: T) : NetworkResult<T>()

    class Error<T : Any>(val error: ErrorEntity.Error?) : NetworkResult<T>()

    class Exception<T : Any>(val e: Throwable) : NetworkResult<T>()
}
