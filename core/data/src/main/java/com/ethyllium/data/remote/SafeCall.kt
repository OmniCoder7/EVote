package com.ethyllium.data.remote

import com.ethyllium.domain.error.NetworkError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext
import com.ethyllium.domain.util.Result

suspend inline fun <reified T> safeCall(block: suspend () -> HttpResponse): Result<T, NetworkError> {
    val res = try {
        block()
    } catch (_: UnresolvedAddressException) {
        return Result.Error(NetworkError.NoInternet)
    } catch (_: Exception) {
        return Result.Error(NetworkError.Unknown)
    } catch (_: SerializationException) {
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.Serialization)
    }
    return responseToResult(res)
}

suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, NetworkError> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch (_: NoTransformationFoundException) {
                Result.Error(NetworkError.Serialization)
            }
        }

        HttpStatusCode.Unauthorized.value -> Result.Error(NetworkError.LoginError.UNAUTHORIZED)
        HttpStatusCode.Conflict.value -> Result.Error(NetworkError.RegisterError.CONFLICT)
        in 500..599 -> Result.Error(NetworkError.ServerError)
        else -> Result.Error(NetworkError.Unknown)
    }
}