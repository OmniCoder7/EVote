package com.ethyllium.invite.state

import com.ethyllium.domain.model.Invite

sealed interface InviteAction {
    data class InviteClick(val invite: Invite): InviteAction
    data object RefreshInvite: InviteAction
}

sealed interface Navigation {
    data class DetailedInvite(val invite: Invite): Navigation
}