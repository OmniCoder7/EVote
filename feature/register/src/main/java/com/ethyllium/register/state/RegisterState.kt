package com.ethyllium.register.state

import com.ethyllium.domain.model.Role

data class RegisterState(
    val firstName: String = "",
    val lastName: String = "",
    val email: EmailState = EmailState(),
    val password: PasswordState = PasswordState(),
    val confirmPassword: ConfirmPasswordState = ConfirmPasswordState(passwordState = password),
    val gender: String = "Male",
    val dob: Long = 0,
    val role: Role = Role.VOTER
)
