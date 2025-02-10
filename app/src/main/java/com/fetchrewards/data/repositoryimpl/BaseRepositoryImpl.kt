package com.fetchrewards.data.repositoryimpl

import android.util.MalformedJsonException
import com.fetchrewards.domain.common.NetworkResult
import com.fetchrewards.domain.constants.NetworkConstants
import com.fetchrewards.domain.entity.ErrorEntity
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

open class BaseRepositoryImpl {
    protected suspend fun <T : Any> apiCall(call: suspend () -> T): NetworkResult<T> {
        return try {
            val response = call()
            NetworkResult.Success(response)
        } catch (ex: Throwable) {
            println("Error Occureed again" + ex.message)
            NetworkResult.Error(handleError(ex))
        }
    }

    /**
     * This method returns the error codes and error messages for various
     * network exceptions and API related errors
     */
    private fun handleError(throwable: Throwable): ErrorEntity.Error {
        println("Error Occureed" + throwable.message)
        return when (throwable) {
            is SocketTimeoutException -> {
                ErrorEntity.Error(
                    NetworkConstants.NetworkErrorCodes.SERVICE_UNAVAILABLE,
                    NetworkConstants.NetworkErrorMessages.SERVICE_UNAVAILABLE,
                )
            }

            is MalformedJsonException -> {
                ErrorEntity.Error(
                    NetworkConstants.NetworkErrorCodes.MALFORMED_JSON,
                    NetworkConstants.NetworkErrorMessages.MALFORMED_JSON,
                )
            }

            is IOException -> {
                ErrorEntity.Error(
                    NetworkConstants.NetworkErrorCodes.NO_INTERNET,
                    NetworkConstants.NetworkErrorMessages.NO_INTERNET,
                )
            }

            is HttpException -> {
                ErrorEntity.Error(
                    NetworkConstants.NetworkErrorCodes.UNEXPECTED_ERROR,
                    NetworkConstants.NetworkErrorMessages.UNEXPECTED_ERROR,
                )
            }
            else -> {
                ErrorEntity.Error(
                    NetworkConstants.NetworkErrorCodes.UNEXPECTED_ERROR,
                    NetworkConstants.NetworkErrorMessages.UNEXPECTED_ERROR,
                )
            }
        }
    }
}
