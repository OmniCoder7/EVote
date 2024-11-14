package com.ethyllium.login

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ethyllium.login.composable.AnimatedPasswordToggle
import com.ethyllium.login.composable.NoTintIcon
import com.ethyllium.login.state.LoginAction
import com.ethyllium.login.state.LoginDestination

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun LoginScreen(modifier: Modifier = Modifier,
                state: LoginState,
                onAction:(LoginAction) -> Unit) {
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NoTintIcon(id = R.drawable.evote)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.feature_login_welcome),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(R.string.feature_login_sign_in_to_continue),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            OutlinedTextField(
                value = state.email,
                onValueChange = { onAction.invoke(LoginAction.EmailChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = stringResource(R.string.feature_login_enter_email),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                keyboardActions = KeyboardActions(onNext = {
                    focusManger.moveFocus(
                        FocusDirection.Down
                    )
                }),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Email
                ),
                leadingIcon = {
                    NoTintIcon(id = R.drawable.email)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                ),
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = { onAction.invoke(LoginAction.PasswordChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = stringResource(R.string.feature_login_enter_password),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardActions = KeyboardActions(onDone = {
                    focusManger.clearFocus()
                    onAction.invoke(LoginAction.Login)
                }),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
                ),
                leadingIcon = {
                    NoTintIcon(id = R.drawable.password)
                },
                trailingIcon = {
                    AnimatedPasswordToggle(isPasswordVisible = state.isPasswordVisible,
                        onToggle = {
                            onAction.invoke(LoginAction.TogglePasswordVisibility)
                        })
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Text(
                text = stringResource(R.string.feature_login_forgot_password),
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { onAction(LoginAction.Navigate(LoginDestination.ForgotPassword)) },
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge
            )

            Button(
                onClick = { onAction.invoke(LoginAction.Login) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = BorderStroke(width = 1.dp, color = Color.White.copy(alpha = 0.5f))
            ) {
                Text(
                    text = stringResource(R.string.feature_login_login),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            val account = buildAnnotatedString {
                append(stringResource(R.string.feature_login_register))
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append(" Create")
                }
            }
            Text(
                text = account,
                modifier = Modifier.clickable { onAction(LoginAction.Navigate(LoginDestination.Register)) },
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}