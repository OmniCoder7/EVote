package com.ethyllium.evote.util

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.ethyllium.domain.model.Voter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val VoterType = object : NavType<Voter>(isNullableAllowed = false) {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = false
        explicitNulls = false
        prettyPrint = true
    }

    override fun get(bundle: Bundle, key: String): Voter {
        return json.decodeFromString(bundle.getString(key)!!)
    }

    override fun parseValue(value: String): Voter {
        return json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: Voter): String {
        return Uri.encode(
            json.encodeToString(
                serializer = Voter.serializer(), value = value
            )
        )
    }

    override fun put(bundle: Bundle, key: String, value: Voter) {
        bundle.putString(key, json.encodeToString(value))
    }
}