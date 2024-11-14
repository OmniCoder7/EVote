package com.ethyllium.invite.state

import com.ethyllium.domain.model.Invite

data class InviteListState(
    val invites: List<Invite>,
    val isLoading: Boolean = false
)
