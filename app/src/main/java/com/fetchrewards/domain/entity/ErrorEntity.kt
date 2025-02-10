package com.fetchrewards.domain.entity

sealed class ErrorEntity {
    data class Error(val errorCode: String? = "", val errorMessage: String? = "") : ErrorEntity()
}
