package com.ethyllium.login

import androidx.compose.runtime.Immutable

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
    val isPasswordVisible: Boolean = false
)