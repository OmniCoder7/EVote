package com.ethyllium.data.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class SignInResponse(
    val user: User,
    val requiresTwoFactor: Boolean = false,
    val verificationId: String? = null,
    val requiresPasswordReset: Boolean = false,
    val blockedUntil: Long? = null,
)