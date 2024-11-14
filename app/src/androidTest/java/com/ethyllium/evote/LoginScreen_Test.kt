package com.ethyllium.evote;

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.ethyllium.evote.ui.theme.EVoteTheme
import com.ethyllium.login.LoginScreen
import com.ethyllium.login.LoginState
import com.ethyllium.login.state.LoginAction
import org.junit.Rule
import org.junit.Test

class LoginScreen_Test {
    @get:Rule val composeTestRule = createComposeRule()
    lateinit var context: Context

    @Test
    fun myTest() {
        // Start the app
        composeTestRule.setContent {
            EVoteTheme() {
                context = LocalContext.current
                LoginScreen(modifier = Modifier.fillMaxSize(),
                    state = LoginState(),
                    onAction = {
                        when(it) {
                            LoginAction.CreateAccount -> Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT)
                            is LoginAction.EmailChanged -> Toast.makeText(context, "Email Changed", Toast.LENGTH_SHORT)
                            LoginAction.ForgotPassword -> Toast.makeText(context, "Forgot Password", Toast.LENGTH_SHORT)
                            LoginAction.Login -> Toast.makeText(context, "Login", Toast.LENGTH_SHORT)
                            is LoginAction.PasswordChanged -> Toast.makeText(context, "Password Changed", Toast.LENGTH_SHORT)
                            LoginAction.TogglePasswordVisibility -> Toast.makeText(context, "Toggle Password Visibility", Toast.LENGTH_SHORT)
                        }
                    })
            }
        }
        composeTestRule.onNodeWithText("Login").performClick()
    }
}