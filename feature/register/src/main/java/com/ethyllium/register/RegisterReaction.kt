package com.ethyllium.register

import com.ethyllium.domain.model.Voter

class RegisterReaction(
    private val onLogin: () -> Unit,
    private val onForgotPassword: () -> Unit,
    private val onHome: (Voter) -> Unit
) {
    operator fun invoke(action: RegisterDestination) {
        return when (action) {
            RegisterDestination.Login -> onLogin.invoke()
            RegisterDestination.ForgotPassword -> onForgotPassword.invoke()
            is RegisterDestination.Home -> onHome(action.voter)
        }
    }
}