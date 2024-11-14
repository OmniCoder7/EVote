package com.ethyllium.resetpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ethyllium.domain.error.NetworkError
import com.ethyllium.domain.service.AuthService
import com.ethyllium.domain.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(
    private val email: String, private val authService: AuthService
) : ViewModel() {

    private val _state = MutableStateFlow(ForgotPasswordState(email = email))
    val state = _state.asStateFlow()

    private val actualOtp = MutableStateFlow<String>("")

    fun onAction(action: ForgotPasswordAction) {
        when (action) {
            is ForgotPasswordAction.EmailChanged -> {
                _state.value = state.value.copy(email = action.email)
            }

            is ForgotPasswordAction.EmailError -> TODO()
            ForgotPasswordAction.Reset -> resetPassword()
            ForgotPasswordAction.SendOTP -> sendOTP()

            is ForgotPasswordAction.OTPChanged -> _state.update { it.copy(enteredOTP = action.otp) }
        }
    }

    private fun resetPassword() {
        viewModelScope.launch {
            val res = authService.resetPassword(state.value.email, state.value.enteredOTP)
            when (res) {
                is Result.Error -> when (res.error) {
                    NetworkError.ResetPasswordError.INVALID_PASSWORD -> _state.update { it.copy(isPasswordReset = false) }
                }

                is Result.Success -> {
                    _state.update { it.copy(isPasswordReset = true) }
                }
            }
        }
    }

    private fun sendOTP() {
        viewModelScope.launch {
            val res = authService.sendOTP(state.value.email)
            when (res) {
                is Result.Error -> when (res.error) {
                    NetworkError.OTPSendError.INVALID_EMAIL -> _state.update { it.copy(isEmailInvalid = true) }
                }

                is Result.Success -> {
                    _state.update { it.copy(isOTPSend = true) }
                    actualOtp.update { res.data }
                }
            }
        }

    }
}