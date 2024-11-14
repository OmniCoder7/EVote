package com.ethyllium.login.state

import com.ethyllium.domain.model.Voter

sealed interface LoginAction {
    data class EmailChanged(val email: String) : LoginAction
    data class PasswordChanged(val password: String) : LoginAction
    data object TogglePasswordVisibility : LoginAction
    data object Login: LoginAction
    data class Navigate(val destination: LoginDestination) : LoginAction
}

sealed interface LoginDestination {
    data object Register: LoginDestination
    data object ForgotPassword: LoginDestination
    data class Home(val voter: Voter): LoginDestination
}