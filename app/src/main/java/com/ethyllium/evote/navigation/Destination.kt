package com.ethyllium.evote.navigation

import android.annotation.SuppressLint
import com.ethyllium.domain.model.Voter
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
sealed interface Destination {

    @Serializable
    data object Login : Destination

    @Serializable
    data object Splash : Destination

    @Serializable
    data object Root : Destination

    @Serializable
    data object Register : Destination

    @Serializable
    data class Home(val voter: Voter = Voter(
        email = "",
        displayName = "Not Wanted",
        authToken = "",
        voterId = 0,
        candidateId = 0,
        adminId = 0,
        profilePicture = null
    )
    ) : Destination

    @Serializable
    data object ForgotPassword : Destination

    @Serializable
    data object Result : Destination

    @Serializable
    data object Invite : Destination

    @Serializable
    data class DetailedInvite(val invite: Invite): Destination

    @Serializable
    data object Upcoming : Destination

    @Serializable
    data object AuthGraph: Destination

    @Serializable
    data object MainGraph: Destination

    @Serializable
    data object InviteGraph: Destination

    @Serializable
    data object ResultGraph: Destination

    @Serializable
    data object UpcomingGraph: Destination
}