package com.ethyllium.root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ethyllium.domain.error.NetworkError
import com.ethyllium.domain.util.Result
import kotlinx.coroutines.launch

class RootViewModel(
    private val rootReaction: RootReaction, private val oAuthClient: OAuthClient
) : ViewModel() {

    fun onAction(rootAction: RootAction) {
        when (rootAction) {
            RootAction.SignInGoogle -> {
                viewModelScope.launch {
                    val res = oAuthClient.login()
                    when (res) {
                        is Result.Error -> when(res.error) {
                            NetworkError.AuthError.UNAUTHORIZED -> {

                            }

                            NetworkError.AuthError.UNKNOWN -> {}
                        }
                        is Result.Success -> rootReaction(RootDestination.Home(res.data))
                    }
                }
            }

            is RootAction.Navigate -> rootReaction(rootAction.destination)
        }
    }
}