package com.ethyllium.resetpassword

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun ForgotPassword(
    modifier: Modifier = Modifier,
    state: ForgotPasswordState,
    onAction: (ForgotPasswordAction) -> Unit,
) {
    val focusManger = LocalFocusManager.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.auth_background),
                contentScale = ContentScale.FillBounds
            )
            .clickable(
                interactionSource = null, indication = null
            ) { focusManger.clearFocus(true) }, contentAlignment = Alignment.Center
    ) {
        TextField(
            value = state.email,
            onValueChange = { onAction(ForgotPasswordAction.EmailChanged(it)) },
            placeholder = { Text(text = stringResource(R.string.feature_forgotPassword_enter_email)) },
            keyboardActions = KeyboardActions(onDone = {
                focusManger.clearFocus()
                onAction(ForgotPasswordAction.SendOTP)
            }),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
            )
        )

        AnimatedVisibility(visible = state.isOTPSend) { Text(text = stringResource(R.string.feature_forgotPassword_otp_send) + state.email) }

        if (state.isOTPSend)
            TextField(
                value = state.enteredOTP.toString(),
                onValueChange = { onAction(ForgotPasswordAction.OTPChanged(it)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { focusManger.clearFocus() })
            )
    }
}