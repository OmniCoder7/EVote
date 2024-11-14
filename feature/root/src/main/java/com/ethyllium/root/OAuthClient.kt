package com.ethyllium.root

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.PasswordCredential
import androidx.credentials.PublicKeyCredential
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import com.ethyllium.domain.error.NetworkError
import com.ethyllium.domain.model.Voter
import com.ethyllium.domain.service.AuthService
import com.ethyllium.domain.util.Result
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

class OAuthClient(
    private val context: Context, private val authService: AuthService
) {
    val googleIdOption: GetGoogleIdOption =
        GetGoogleIdOption.Builder().setFilterByAuthorizedAccounts(true)
            .setServerClientId(BuildConfig.CLIENT_ID)
            .setAutoSelectEnabled(true)
            .setNonce(getNonce()).build()

    val credentialManager = CredentialManager.create(context)

    private fun getNonce(): String {
        return ""
    }

    suspend fun login(): Result<Voter, NetworkError.AuthError> {
        try {
            val request = GetCredentialRequest.Builder().addCredentialOption(googleIdOption).build()
            val credential = credentialManager.getCredential(
                context = context, request = request
            ).credential
            when (credential) {
                is PublicKeyCredential -> {
                    Log.d("OAuthClient", "login: ${credential.authenticationResponseJson}")
                }

                is PasswordCredential -> {
                    return authService.authenticate(credential.id, credential.password)
                }

                is CustomCredential -> {
                    val res = GoogleIdTokenCredential.createFrom(credential.data)
                    return authService.authenticate(
                        res.idToken, Voter(
                            email = res.givenName ?: "",
                            profilePicture = res.profilePictureUri?.toString() ?: "",
                            displayName = res.displayName ?: "",
                        )
                    )
                }

                else -> {
                    return Result.Error(NetworkError.AuthError.UNAUTHORIZED)
                }
            }
        } catch (e: GetCredentialCancellationException) {
            e.printStackTrace()
        } catch (e: GetCredentialException) {
            e.printStackTrace()
        }
        return Result.Error(NetworkError.AuthError.UNAUTHORIZED)
    }
}