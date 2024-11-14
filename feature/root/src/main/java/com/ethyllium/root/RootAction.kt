package com.ethyllium.root

import com.ethyllium.domain.model.Voter

sealed interface RootAction {
    object SignInGoogle : RootAction
    data class Navigate(val destination: RootDestination) : RootAction
}

sealed interface RootDestination {
    data object Login : RootDestination
    data class Home(val voter: Voter) : RootDestination
    data object Register : RootDestination
}