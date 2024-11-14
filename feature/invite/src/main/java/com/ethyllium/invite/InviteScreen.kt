package com.ethyllium.invite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ethyllium.domain.model.Invite
import com.ethyllium.invite.composable.NoTintIcon

@Composable
fun InviteScreen(
    modifier: Modifier = Modifier, invite: Invite
) {
    Column(
        modifier = modifier.padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Title(invite = invite, modifier = Modifier.fillMaxWidth())
        Text(text = invite.electionDescription, modifier = Modifier.fillMaxWidth())
        HorizontalDivider()
        Dates(invite = invite, modifier = Modifier.fillMaxWidth())
        VoterInfo(invite = invite, modifier = Modifier.fillMaxWidth())
        Footer(invite = invite, modifier = Modifier.fillMaxWidth())
        HorizontalDivider()
        Text(
            text = "Invite sent on ${invite.inviteSentDate}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun Title(
    modifier: Modifier = Modifier, invite: Invite
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        BadgedBox(badge = {
            Badge(containerColor = Color.Transparent) {
                NoTintIcon(
                    id = if (invite.conductingAuthority.verified) R.drawable.verified else R.drawable.not_verified,
                    modifier = Modifier.size(12.dp)
                )
            }
        }) {
            Card(shape = CircleShape,
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)) {
                AsyncImage(
                    model = invite.conductingAuthority.imageUri,
                    contentDescription = invite.conductingAuthority.title,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
            }
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = invite.electionTitle, style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = invite.electionSubtitle, style = MaterialTheme.typography.titleMedium.copy(color = Color.Blue)
            )
        }
    }
}

@Composable
private fun Dates(
    modifier: Modifier = Modifier, invite: Invite
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10))
            .background(color = Color(0xFF8A9FCC)),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(top = 16.dp, end = 16.dp, start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            NoTintIcon(id = R.drawable.calendar)
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.feature_invite_starts), color = Color(0xFF526CCC)
                )
                Text(
                    text = invite.startDate
                )
            }
        }
        Row(
            modifier = Modifier.padding(bottom = 16.dp, end = 16.dp, start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            NoTintIcon(id = R.drawable.clock)
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = stringResource(R.string.feature_invite_ends), color = Color(0xFFCC5353))
                Text(
                    text = invite.lastDate
                )
            }
        }
    }
}

@Composable
private fun VoterInfo(
    modifier: Modifier = Modifier, invite: Invite
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10))
            .background(color = Color(0xFFDA6565)),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp, end = 16.dp, start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            NoTintIcon(id = R.drawable.profile)
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = stringResource(R.string.feature_invite_voter_id),
                    color = Color(0xFF595D69),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(text = invite.voterId)
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp, end = 16.dp, start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            NoTintIcon(id = R.drawable.tag)
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = stringResource(R.string.feature_invite_category),
                    color = Color(0xFF595D69)
                )
                Text(text = invite.category)
            }
        }
    }
}

@Composable
private fun Footer(
    modifier: Modifier = Modifier, invite: Invite
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10))
            .background(color = Color(0xFF95D787)),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = stringResource(R.string.feature_invite_support),
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(25))
        ) {
            NoTintIcon(id = R.drawable.email, modifier = Modifier.padding(8.dp))
            Text(
                text = invite.conductingAuthority.supportEmail, modifier = Modifier.weight(1f),
            )
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(25))
        ) {
            NoTintIcon(id = R.drawable.call, modifier = Modifier.padding(8.dp))
            Text(
                text = invite.conductingAuthority.supportNumber, modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun Invite_Prev() {
    InviteScreen(
        invite = Invite(
            electionTitle = "2024 Board Member Election",
            electionSubtitle = "Annual Board Selection",
            electionDescription = "Cast your vote to elect new board members who will represent our organization for the next term. Your participation is crucial for maintaining democratic governance",
            startDate = "2024-01-01",
            lastDate = "2024-01-15",
            inviteSentDate = "2023-12-25",
        )
    )
}