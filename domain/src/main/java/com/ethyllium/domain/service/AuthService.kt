package com.ethyllium.domain.service

import com.ethyllium.domain.error.NetworkError
import com.ethyllium.domain.model.Role
import com.ethyllium.domain.model.Voter
import com.ethyllium.domain.util.Result

interface AuthService {
    suspend fun login(email: String, password: String): Result<Voter, NetworkError.LoginError>
    suspend fun register(voter: Voter, password: String, role: Role): Result<Voter, NetworkError.RegisterError>
    suspend fun authenticate(): Result<Voter, NetworkError.AuthError>
    suspend fun authenticate(username: String, password: String): Result<Voter, NetworkError.AuthError>
    suspend fun authenticate(idTokenString: String, voter: Voter): Result<Voter, NetworkError.AuthError>
}