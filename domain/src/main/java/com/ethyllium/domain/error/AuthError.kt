package com.ethyllium.domain.error

sealed interface NetworkError : Error {

    data object NoInternet : NetworkError
    data object Unknown : NetworkError
    data object Serialization : NetworkError
    data object ServerError : NetworkError

    enum class LoginError : NetworkError {
        UNAUTHORIZED,
        UNKNOWN
    }

    enum class RegisterError : NetworkError {
        CONFLICT,
        UNKNOWN
    }

    enum class AuthError : NetworkError {
        UNAUTHORIZED,
        UNKNOWN
    }

}