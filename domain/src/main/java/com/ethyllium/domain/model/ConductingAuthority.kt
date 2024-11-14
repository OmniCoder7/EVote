package com.ethyllium.domain.model

data class ConductingAuthority(
    val imageUri: String,
    val verified: Boolean,
    val supportEmail: String,
    val supportNumber: String,
    val title: String
)
