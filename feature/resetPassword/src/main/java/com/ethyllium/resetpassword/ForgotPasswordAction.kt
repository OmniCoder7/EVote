package com.ethyllium.resetpassword

sealed interface ForgotPasswordAction {
    data object SendOTP: ForgotPasswordAction
    data object Reset: ForgotPasswordAction

    data class EmailChanged(val email: String): ForgotPasswordAction
    data class OTPChanged(val otp: String): ForgotPasswordAction
    data class EmailError(val error: String): ForgotPasswordAction
}