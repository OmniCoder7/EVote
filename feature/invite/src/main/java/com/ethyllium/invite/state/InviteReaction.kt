package com.ethyllium.invite.state

import com.ethyllium.domain.model.Invite

class InviteReaction(
    val toDetailedInvite: (Invite) -> Unit
) {
    operator fun invoke(action: Navigation) {
        when(action) {
            is Navigation.DetailedInvite -> toDetailedInvite.invoke(action.invite)
        }
    }
}
