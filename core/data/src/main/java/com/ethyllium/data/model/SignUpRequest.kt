package com.ethyllium.data.model

import android.annotation.SuppressLint
import com.ethyllium.domain.model.Role
import com.ethyllium.domain.model.Voter
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class SignUpRequest(
    val username: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val role: Role
) {
    constructor(voter: Voter, password: String, role: Role): this(
        username = voter.username,
        password = password,
        email = voter.email,
        firstName = voter.displayName,
        lastName = voter.displayName,
        phoneNumber = voter.phoneNumber,
        role = role
    )
}