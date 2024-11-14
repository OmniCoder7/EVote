package com.ethyllium.root

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ethyllium.domain.model.Voter

@Composable
fun RootScreen(
    modifier: Modifier = Modifier, rootAction: (RootAction) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.auth_background),
                contentScale = ContentScale.FillBounds
            ), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text(text = stringResource(R.string.feature_root_sign_in), modifier = Modifier
                .border(
                    2.dp, color = Color.Black, shape = RoundedCornerShape(40)
                )
                .padding(8.dp)
                .clickable { rootAction(RootAction.Navigate(RootDestination.Login)) })
            Text(text = stringResource(R.string.feature_root_register), modifier = Modifier
                .border(
                    2.dp, color = Color.Black, shape = RoundedCornerShape(40)
                )
                .padding(8.dp)
                .clickable { rootAction(RootAction.Navigate(RootDestination.Register)) })
            Text(text = stringResource(R.string.feature_root_guest), modifier = Modifier
                .border(
                    2.dp, color = Color.Black, shape = RoundedCornerShape(40)
                )
                .padding(8.dp)
                .clickable { rootAction(RootAction.Navigate(RootDestination.Home(Voter(
                    email = "",
                    displayName = "New User",
                    authToken = "",
                    voterId = 0,
                    candidateId = 0,
                    adminId = 0,
                    profilePicture = null
                )
                ))) })
            Text(text = stringResource(R.string.feature_root_sign_in_google),
                modifier = Modifier
                    .border(
                        2.dp, color = Color.Black, shape = RoundedCornerShape(40)
                    )
                    .padding(8.dp)
                    .clickable { rootAction(RootAction.SignInGoogle) })
        }
    }
}