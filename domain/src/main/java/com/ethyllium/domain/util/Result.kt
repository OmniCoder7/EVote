package com.ethyllium.domain.util

import com.ethyllium.domain.error.Error

typealias RootError = Error

sealed interface Result<out D, out E: RootError> {
    data class Success<out D, out E: RootError>(val data: D): Result<D, E>
    data class Error<out D, out E: RootError>(val error: E): Result<D, E>
}

inline fun <D, E: RootError, R> Result<D, E>.map(transform: (D) -> R): Result<R, E> = when (this) {
    is Result.Success -> Result.Success(transform(this.data))
    is Result.Error -> Result.Error(this.error)
}