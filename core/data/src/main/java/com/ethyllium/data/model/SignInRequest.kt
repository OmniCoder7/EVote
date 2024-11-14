package com.ethyllium.data.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class SignInRequest(
    val username: String,
    val password: String
)
