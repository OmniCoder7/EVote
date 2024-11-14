package com.ethyllium.register.state

import com.ethyllium.domain.error.PasswordError
import com.ethyllium.domain.util.PasswordValidator
import com.ethyllium.domain.util.Result
import com.ethyllium.register.R

class PasswordState :
    TextFieldState(validator = ::isPasswordValid, errorFor = ::passwordValidationError)

class ConfirmPasswordState(
    private val passwordState: PasswordState
) : TextFieldState() {
    override val isValid
        get() = passwordAndConfirmationValid(passwordState.text, text)

    override fun getError(): UIText? {
        return if (showErrors()) {
            passwordConfirmationError()
        } else {
            null
        }
    }
}

private fun passwordAndConfirmationValid(password: String, confirmedPassword: String): Boolean {
    return PasswordValidator.isPasswordValid(password) is Result.Success && password == confirmedPassword
}

private fun isPasswordValid(password: String): Boolean {
    return when (PasswordValidator.isPasswordValid(password)) {
        is Result.Error -> false
        is Result.Success -> true
    }
}

private fun passwordValidationError(password: String): UIText {
    return when (val passwordValidity = PasswordValidator.isPasswordValid(password)) {
        is Result.Error -> when (passwordValidity.error) {
            PasswordError.BLANK -> UIText.StringResource(R.string.feature_register_password_blank)
            PasswordError.TOO_SHORT -> UIText.StringResource(R.string.feature_register_too_short)
            PasswordError.NO_LOWERCASE -> UIText.StringResource(R.string.feature_register_no_lowercase)
            PasswordError.NO_UPPERCASE -> UIText.StringResource(R.string.feature_register_no_uppercase)
            PasswordError.NO_DIGIT -> UIText.StringResource(R.string.feature_register_no_digit)
            PasswordError.NO_SPECIAL_CHAR -> UIText.StringResource(R.string.feature_register_no_special_char)
        }

        is Result.Success -> UIText.DynamicString("No Error")
    }
}

private fun passwordConfirmationError(): UIText {
    return UIText.StringResource(R.string.feature_register_password_mismatch)
}