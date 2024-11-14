package com.ethyllium.data.remote

import com.ethyllium.data.BuildConfig

sealed class ApiEndpoints(val path: String) {
    data object Login : ApiEndpoints(BuildConfig.BASE_URL + "auth/signin")
    data object Register : ApiEndpoints(BuildConfig.BASE_URL + "auth/register")
    data object Authenticate : ApiEndpoints(BuildConfig.BASE_URL + "auth/authenticate")
}