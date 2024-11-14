package com.ethyllium.login.state

import com.ethyllium.domain.model.Voter

class LoginReaction(
    private val onRegister: () -> Unit,
    private val onForgotPassword: () -> Unit,
    private val onHome: (Voter) -> Unit
) {
    operator fun invoke(action: LoginDestination) {
        return when (action) {
            LoginDestination.Register -> onRegister.invoke()
            LoginDestination.ForgotPassword -> onForgotPassword.invoke()
            is LoginDestination.Home -> onHome.invoke(action.voter)
        }
    }
}