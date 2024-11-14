package com.ethyllium.invite

import androidx.lifecycle.ViewModel
import com.ethyllium.invite.state.InviteAction
import com.ethyllium.invite.state.InviteListState
import com.ethyllium.invite.state.InviteReaction
import com.ethyllium.invite.state.Navigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InviteViewModel(
    private val inviteReaction: InviteReaction
): ViewModel() {

    private val _state: MutableStateFlow<InviteListState> = MutableStateFlow(
        InviteListState(
            emptyList()
        )
    )
    val state = _state.asStateFlow()

    fun onAction(action: InviteAction) {
        when(action) {
            is InviteAction.InviteClick -> {
                inviteReaction(Navigation.DetailedInvite(action.invite))
            }

            InviteAction.RefreshInvite -> updateInvites()
        }
    }
    private fun updateInvites() {

    }
}