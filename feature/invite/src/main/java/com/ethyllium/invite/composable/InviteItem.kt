package com.ethyllium.invite.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ethyllium.domain.model.Invite
import com.ethyllium.invite.R

@Composable
fun InviteItem(
    modifier: Modifier = Modifier, invite: Invite
) {
    Card(
        modifier = modifier.padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(5)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Header(invite = invite)
            MainTitle(invite = invite)
            Dates(invite = invite)
            Footer(invite = invite)
        }
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier, invite: Invite
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        AsyncImage(
            model = invite.conductingAuthority.imageUri,
            contentDescription = null,
            placeholder = painterResource(R.drawable.building),
            modifier = Modifier.size(40.dp)
        )
        Text(text = invite.conductingAuthority.title)
        if (invite.conductingAuthority.verified) NoTintIcon(id = R.drawable.verified)
        else NoTintIcon(id = R.drawable.not_verified)
    }
}

@Composable
private fun MainTitle(
    modifier: Modifier = Modifier, invite: Invite
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xFF2563eb))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = invite.electionTitle,
                softWrap = true,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = invite.electionSubtitle, style = MaterialTheme.typography.titleMedium
            )
        }
        NoTintIcon(id = R.drawable.calendar)
    }
}

@Composable
private fun Dates(
    modifier: Modifier = Modifier, invite: Invite
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NoTintIcon(id = R.drawable.clock, tint = Color.Green)
                Text(text = stringResource(R.string.feature_invite_starts))
            }
            Text(text = invite.startDate)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NoTintIcon(id = R.drawable.clock, tint = Color.Red)
                Text(text = stringResource(R.string.feature_invite_ends))
            }
            Text(text = invite.lastDate)
        }
    }
}


@Composable
private fun Footer(
    modifier: Modifier = Modifier, invite: Invite
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        Text(text = "Invite send on: ${invite.inviteSentDate}")
        Button(onClick = {}) {
            Text(text = stringResource(R.string.feature_invite_vote_now))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}