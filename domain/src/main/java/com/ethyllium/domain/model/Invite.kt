package com.ethyllium.domain.model

import java.time.LocalDateTime

data class Invite(
    val electionTitle: String,
    val electionSubtitle: String,
    val electionDescription: String,
    val startDate: String,
    val lastDate: String,
    val inviteSentDate: String,
    val conductingAuthority: ConductingAuthority = ConductingAuthority(
        imageUri = "https://scontent-ams2-1.xx.fbcdn.net/v/t39.30808-1/449525937_803685755200100_9164894160643207251_n.jpg?stp=c27.27.186.186a_dst-jpg_p240x240&_nc_cat=100&ccb=1-7&_nc_sid=f4b9fd&_nc_ohc=wzX1BLWgATMQ7kNvgGDRWEF&_nc_zt=24&_nc_ht=scontent-ams2-1.xx&_nc_gid=AHJRg33XX9kA9BJC75S9sUG&oh=00_AYDcnkZKJRbHyOvZwsoD37HZTgQ6FARND3JIqvLOHm_QUA&oe=672C59BC",
        verified = true,
        supportEmail = "nitsri.ac.in",
        supportNumber = "0191-2465027",
        title = "NIT Srinagar"
    ),
    val voterId: String = "VOT-32948-234",
    val category: String = "Regular Member"
)