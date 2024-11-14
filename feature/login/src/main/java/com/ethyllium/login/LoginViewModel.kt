package com.ethyllium.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ethyllium.domain.error.NetworkError
import com.ethyllium.domain.service.AuthService
import com.ethyllium.domain.util.Result
import com.ethyllium.login.state.LoginAction
import com.ethyllium.login.state.LoginDestination
import com.ethyllium.login.state.LoginReaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginReaction: LoginReaction, private val authService: AuthService
) : ViewModel() {

    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private fun login() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val loginRes = authService.login(
                email = "viskinSaka@gmail.com", password = ""
            )
            when (loginRes) {
                is Result.Error -> when (loginRes.error) {
                    NetworkError.LoginError.UNAUTHORIZED -> {}
                    NetworkError.LoginError.UNKNOWN -> {}
                }

                is Result.Success -> {
                    _state.update { it.copy(isLoading = false) }
                    loginReaction(LoginDestination.Home(loginRes.data))
                }
            }
        }
    }

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.EmailChanged -> _state.update { it.copy(email = action.email) }
            is LoginAction.PasswordChanged -> _state.update { it.copy(password = action.password) }
            is LoginAction.TogglePasswordVisibility -> _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            is LoginAction.Navigate -> when (action.destination) {
                is LoginDestination.Home, LoginDestination.Register, LoginDestination.ForgotPassword -> loginReaction(
                    action.destination
                )
            }
            LoginAction.Login -> login()
        }
    }
}