package com.ethyllium.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ethyllium.domain.error.NetworkError
import com.ethyllium.domain.model.Voter
import com.ethyllium.domain.service.AuthService
import com.ethyllium.domain.util.Result
import com.ethyllium.register.state.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerReaction: RegisterReaction, private val authService: AuthService
) : ViewModel() {

    private val _state: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onAction(action: RegisterAction) {
        when (action) {
            RegisterAction.Register -> register()

            is RegisterAction.NameChanged -> _state.update {
                val name = splitName(action.name)
                it.copy(firstName = name.first, lastName = name.second)
            }

            is RegisterAction.RoleChanged -> _state.update { it.copy(role = action.role) }
            is RegisterAction.Navigate -> registerReaction(action.destination)
        }
    }

    private fun register() {
        viewModelScope.launch {
            val registerRes = authService.register(
//                Voter(
//                    email = state.value.email.text,
//                    displayName = "${state.value.firstName} ${state.value.lastName}",
//                    authToken = "",
//                    voterId = null,
//                    candidateId = null,
//                    adminId = null,
//                    profilePicture = null
//                ), password = state.value.password.text,
                voter = Voter(
                    username = "viskin",
                    email = "viskinSaka@gmail.com",
                    displayName = "Viskin Saka",
                    phoneNumber = "98479347",
                    profilePicture = null
                ),
                role = state.value.role,
                password = state.value.password.text
            )
            when (registerRes) {
                is Result.Error -> when (registerRes.error) {
                    NetworkError.RegisterError.CONFLICT -> _state.update {
                        it.apply { email.text = "Email already exists" }
                    }

                    else -> _state.update {
                        it.apply { email.text = "An error occurred" }
                    }
                }

                is Result.Success -> registerReaction(
                    RegisterDestination.Home(registerRes.data)
                )
            }
        }

    }

    private fun splitName(input: String): Pair<String, String> {
        val parts = input.trim().split(" ", limit = 2)
        return when {
            parts.isEmpty() -> Pair("", "")
            parts.size == 1 -> Pair(parts[0], "")
            else -> Pair(parts[0], parts[1])
        }
    }
}