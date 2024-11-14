package com.ethyllium.root

import com.ethyllium.domain.model.Voter

class RootReaction(
    private val onRegister: () -> Unit,
    private val onHome: (Voter) -> Unit,
    private val onLogin: () -> Unit
) {
    operator fun invoke(action: RootDestination) {
        when (action) {
            RootDestination.Register -> onRegister.invoke()
            is RootDestination.Home -> onHome.invoke(action.voter)
            RootDestination.Login -> onLogin.invoke()
        }
    }
}