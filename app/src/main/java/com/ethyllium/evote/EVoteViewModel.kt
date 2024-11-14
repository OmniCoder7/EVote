package com.ethyllium.evote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ethyllium.domain.error.NetworkError
import com.ethyllium.domain.model.Voter
import com.ethyllium.domain.service.AuthService
import com.ethyllium.domain.util.Result
import com.ethyllium.evote.navigation.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EVoteViewModel(
    private val authService: AuthService
): ViewModel() {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val res = authService.authenticate()
            when (res) {
                is Result.Error -> _state.update { UiState.Unauthenticated }
                is Result.Success -> setUser(res.data)
            }

        }
    }

    fun setUser(voter: Voter) {
        _state.update { UiState.Authenticated(voter) }
    }
}

sealed class UiState(val startDestination: Destination) {
    data class Authenticated(val voter: Voter): UiState(Destination.MainGraph)
    data object Unauthenticated: UiState(Destination.AuthGraph)
    data object Loading: UiState(Destination.Splash)
}