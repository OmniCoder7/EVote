package com.ethyllium.data.service

import com.ethyllium.data.model.SignInRequest
import com.ethyllium.data.model.SignInResponse
import com.ethyllium.data.model.SignUpRequest
import com.ethyllium.data.model.User
import com.ethyllium.data.remote.ApiEndpoints
import com.ethyllium.domain.error.NetworkError
import com.ethyllium.domain.model.Role
import com.ethyllium.domain.model.Voter
import com.ethyllium.domain.service.AuthService
import com.ethyllium.domain.util.Result
import com.orhanobut.logger.Logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory

class AuthServiceImpl(
    private val httpClient: HttpClient, private val dataStoreService: DataStoreService
) : AuthService {

    val logger = LoggerFactory.getLogger(AuthServiceImpl::class.java)

    override suspend fun login(
        email: String, password: String
    ): Result<Voter, NetworkError.LoginError> {
        delay(1_000)
        val res = httpClient.post(ApiEndpoints.Login.path) {
            setBody(SignInRequest(username = email, password = password))
        }
        return when(res.status.value) {
            HttpStatusCode.Unauthorized.value -> return Result.Error(NetworkError.LoginError.UNAUTHORIZED)
            HttpStatusCode.OK.value -> {
                val accessToken = res.headers[HttpHeaders.Authorization] ?: return Result.Error(NetworkError.LoginError.UNKNOWN)
                logger.info("User logged in with $accessToken")
                val refreshToken = res.headers["Refresh-Token"]
                val user: SignInResponse = res.body()
                if (refreshToken != null) dataStoreService.write(text = refreshToken.substringAfter("Bearer "), fileName = DataStoreService.REFRESH_TOKEN)
                return Result.Success(user.user.toVoter(accessToken))
            }
            else -> {
                logger.info("Received an status code of ${res.status} while logging in")
                return Result.Error(NetworkError.LoginError.UNKNOWN)
            }
        }
    }

    override suspend fun register(
        voter: Voter, password: String, role: Role
    ): Result<Voter, NetworkError.RegisterError> {
        val res = httpClient.post(ApiEndpoints.Register.path) {
            setBody(SignUpRequest(voter = voter, password = password, role = role))
        }
        return when (res.status.value) {
            HttpStatusCode.Conflict.value -> return Result.Error(NetworkError.RegisterError.CONFLICT)
            HttpStatusCode.OK.value -> {
                val accessToken = res.headers[HttpHeaders.Authorization] ?: return Result.Error(
                    NetworkError.RegisterError.UNKNOWN
                )
                logger.info("User registered with $accessToken")
                val refreshToken = res.headers["Refresh-Token"]
                if (refreshToken != null) dataStoreService.write(
                    text = refreshToken.substringAfter("Bearer "),
                    fileName = DataStoreService.REFRESH_TOKEN
                )
                return Result.Success(voter.copy(authToken = accessToken))
            }

            else -> {
                logger.info("Received an status code of ${res.status} while registering")
                return Result.Error(NetworkError.RegisterError.UNKNOWN)
            }
        }
    }

    override suspend fun authenticate(): Result<Voter, NetworkError.AuthError> {
        var refreshToken =
            dataStoreService.read(DataStoreService.REFRESH_TOKEN) ?: return Result.Error(

                NetworkError.AuthError.UNAUTHORIZED
            )
        val res = httpClient.post(ApiEndpoints.Authenticate.path) {
            headers {
                append(HttpHeaders.Authorization, "Bearer $refreshToken")
            }
        }
        when (res.status.value) {
            HttpStatusCode.Unauthorized.value -> return Result.Error(NetworkError.AuthError.UNAUTHORIZED)
            else -> {
                Logger.i("Received an status code of ${res.status}")
            }
        }
        val user = res.body<User>()
        Logger.i("User authenticated with $user")
        val accessToken: String = res.headers[HttpHeaders.Authorization] ?: run {
            return Result.Error(NetworkError.AuthError.UNKNOWN)
        }
        res.headers["Refresh-Token"] ?: run {
            return Result.Error(NetworkError.AuthError.UNKNOWN)
        }
        dataStoreService.write(text = refreshToken, fileName = DataStoreService.REFRESH_TOKEN)
        return Result.Success(user.toVoter(accessToken))
    }

    override suspend fun authenticate(
        username: String, password: String
    ): Result<Voter, NetworkError.AuthError> {
        delay(1_000)

        return Result.Success(
            Voter(
                email = "vikinSaka@gmail.com",
                displayName = "authenticated Viskin Saka",
                authToken = "",
                voterId = 234,
                candidateId = 39485,
                adminId = 9876,
                profilePicture = "https://static.vecteezy.com/system/resources/previews/001/840/612/non_2x/picture-profile-icon-male-icon-human-or-people-sign-and-symbol-free-vector.jpg"
            )
        )
    }

    override suspend fun authenticate(
        idTokenString: String, voter: Voter
    ): Result<Voter, NetworkError.AuthError> {
        delay(1_000)
        return Result.Success(
            Voter(
                email = "vikinSaka@gmail.com",
                displayName = "Viskin Saka",
                authToken = "",
                voterId = 234,
                candidateId = 39485,
                adminId = 9876,
                profilePicture = "https://static.vecteezy.com/system/resources/previews/001/840/612/non_2x/picture-profile-icon-male-icon-human-or-people-sign-and-symbol-free-vector.jpg"
            )
        )
    }
}