package com.ethyllium.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ethyllium.domain.model.Role
import com.ethyllium.register.composable.Email
import com.ethyllium.register.composable.GroupedRadioButton
import com.ethyllium.register.composable.NoTintIcon
import com.ethyllium.register.composable.Password
import com.ethyllium.register.state.ConfirmPasswordState
import com.ethyllium.register.state.EmailState
import com.ethyllium.register.state.PasswordState
import com.ethyllium.register.state.RegisterState

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier, state: RegisterState, onAction: (RegisterAction) -> Unit
) {
    val focusManager = LocalFocusManager.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.auth_background),
                contentScale = ContentScale.FillBounds
            )
            .clickable(
                interactionSource = null, indication = null
            ) { focusManager.clearFocus(true) }, contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            NoTintIcon(id = R.drawable.evote, modifier = Modifier.size(100.dp))
            Text(
                text = stringResource(R.string.feature_register_welcome),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = stringResource(R.string.feature_register_register_to_continue),
                fontSize = 16.sp,
            )
            TextField(
                value = state.firstName + state.lastName,
                onValueChange = { onAction(RegisterAction.NameChanged(it)) },
                label = { Text(text = stringResource(R.string.enter_first_name)) },
                leadingIcon = { NoTintIcon(id = R.drawable.name) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent
                )
            )
            Email(
                emailState = state.email,
                imeAction = ImeAction.Next,
                onImeAction = { focusManager.moveFocus(FocusDirection.Down) },
            )
            Password(
                label = stringResource(R.string.enter_password),
                passwordState = state.password,
                imeAction = ImeAction.Next,
                onImeAction = { focusManager.moveFocus(FocusDirection.Down) },
            )
            Password(
                label = stringResource(R.string.confirm_password),
                passwordState = state.confirmPassword,
                imeAction = ImeAction.Done,
                onImeAction = { focusManager.moveFocus(FocusDirection.Down) },
            )
            GroupedRadioButton<Role>(items = Role.entries,
                onClick = {
                    onAction(RegisterAction.RoleChanged(it))
                },
                selectedItem = Role.VOTER
            )
            Button(
                onClick = { onAction.invoke(RegisterAction.Register) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = BorderStroke(width = 1.dp, color = Color.White.copy(alpha = 0.5f))
            ) {
                Text(
                    text = stringResource(R.string.feature_register_register),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LKLKKL() {
    val passwordState = PasswordState()
    RegisterScreen(state = RegisterState(
        firstName = "",
        lastName = "",
        email = EmailState(),
        password = passwordState,
        confirmPassword = ConfirmPasswordState(
            passwordState = passwordState
        )
    ), modifier = Modifier, onAction = {})
}