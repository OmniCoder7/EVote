package com.ethyllium.invite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ethyllium.domain.model.Invite
import com.ethyllium.invite.composable.InviteItem
import com.ethyllium.invite.state.InviteAction
import com.ethyllium.invite.state.InviteListState
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun InviteListScreen(
    modifier: Modifier = Modifier, inviteListState: InviteListState, onAction: (InviteAction) -> Unit
) {
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        LazyColumn {
            items(inviteListState.invites) { invite ->
                InviteItem(invite = invite, modifier = Modifier.clickable {
                    onAction(InviteAction.InviteClick(invite))
                })
            }
        }
    }
}
