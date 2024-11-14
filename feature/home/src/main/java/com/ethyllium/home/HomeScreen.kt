package com.ethyllium.home

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ethyllium.domain.model.Voter

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, state: HomeState, voter: Voter,
    copyToClipboard: (Context, String, String) -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFEF4059),
                    ),
                    border = BorderStroke(2.dp, Color(0xFFA22235))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            8.dp, Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    ) {
                        NoTintIcon(id = R.drawable.live_voting)
                        Text(text = stringResource(R.string.feature_home_live_voting))
                    }
                }

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF18A44C),
                    ),
                    border = BorderStroke(2.dp, Color(0xFF186936))
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(
                            8.dp, Alignment.CenterVertically
                        ),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    ) {
                        NoTintIcon(id = R.drawable.profile)
                        Column(
                            verticalArrangement = Arrangement.spacedBy(
                                4.dp, Alignment.CenterVertically
                            ),
                            modifier = Modifier.scrollable(
                                rememberScrollState(), Orientation.Vertical
                            ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(text = voter.displayName)
                            if (voter.voterId != null)
                                Text(text = voter.voterId.toString(),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.clickable { copyToClipboard(context, voter.voterId.toString(), "VoterId") })
                            if (voter.candidateId != null)
                                Text(text = voter.candidateId.toString(),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.clickable { copyToClipboard(context, voter.candidateId.toString(), "CandidateID") })
                            if (voter.adminId != null)
                                Text(text = voter.adminId.toString(),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.clickable { copyToClipboard(context, voter.adminId.toString(), "AdminId") })
                        }
                    }
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF3869F1),
                    ),
                    border = BorderStroke(2.dp, Color(0xFF22429A))
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = stringResource(R.string.feature_home_recent_elections))
                        state.recentElections.forEach {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                NoTintIcon(id = R.drawable.recent)
                                Text(text = it)
                            }
                        }
                    }
                }

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFDF455),
                    ),
                    border = BorderStroke(2.dp, Color(0xFF948F2F))
                ) {
                    Text(
                        text = stringResource(R.string.feature_home_my_achievements),
                        modifier = Modifier.padding(8.dp)
                    )
                    state.achievements.forEach {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(32.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            NoTintIcon(id = it.rarity.id, modifier = Modifier.size(24.dp))
                            Text(text = it.title)
                        }
                    }
                }
            }
        }
    }
}