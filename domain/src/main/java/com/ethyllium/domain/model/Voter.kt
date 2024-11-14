package com.ethyllium.domain.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Voter(
    val username: String = "",
    val email: String = "",
    val displayName: String = "No try something else",
    val authToken: String = "",
    val phoneNumber: String = "",
    val voterId: Int? = null,
    val candidateId: Int? = null,
    val adminId: Int? = null,
    val profilePicture: String?,
)