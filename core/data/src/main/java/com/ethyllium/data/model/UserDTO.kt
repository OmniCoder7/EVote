package com.ethyllium.data.model

import android.annotation.SuppressLint
import com.ethyllium.domain.model.Voter
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class User(
    val userName: String,
    val hashedPassword: String,
    val email: String,
    val roles: List<String>,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
) {
    fun toVoter(authToken: String) =
        Voter(
            email = this.email,
            displayName = this.firstName + " " + this.lastName,
            authToken = authToken,
            profilePicture = null
        )
}