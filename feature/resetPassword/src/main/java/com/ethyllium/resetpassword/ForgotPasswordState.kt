package com.ethyllium.resetpassword

data class ForgotPasswordState(
    val isEmailVisible: Boolean = true,
    val isOTPSend: Boolean = false,
    val isOTPVerified: Boolean = false,
    val canEditPassword: Boolean = false,
    val email: String = "",
    val password: String = "",
    val enteredOTP: String = "",
    val isEmailInvalid: Boolean? = null,
    val isPasswordReset: Boolean? = null
)
