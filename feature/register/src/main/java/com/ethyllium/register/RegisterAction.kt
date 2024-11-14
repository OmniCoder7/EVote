package com.ethyllium.register

import com.ethyllium.domain.model.Role
import com.ethyllium.domain.model.Voter

sealed interface RegisterAction {
    data object Register: RegisterAction
    data class NameChanged(val name: String): RegisterAction
    data class RoleChanged(val role: Role): RegisterAction
    data class Navigate(val destination: RegisterDestination) : RegisterAction
}



sealed interface RegisterDestination {
    data object Login: RegisterDestination
    data object ForgotPassword: RegisterDestination
    data class Home(val voter: Voter): RegisterDestination
}