package com.eatbee.domain.common

sealed class AppResult<out T> {
    data object Loading : AppResult<Nothing>()
    data class Success<out T>(val data: T) : AppResult<T>()
    data class Error(val exception: Throwable) : AppResult<Nothing>()
}

fun <T> Result<T>.toAppResult(): AppResult<T> {
    return fold(
        onSuccess = { AppResult.Success(it) },
        onFailure = { AppResult.Error(it) }
    )
}