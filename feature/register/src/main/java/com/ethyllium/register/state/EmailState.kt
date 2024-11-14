package com.ethyllium.register.state

import android.util.Patterns
import com.ethyllium.domain.util.EmailValidator
import com.ethyllium.register.R
import com.ethyllium.domain.error.EmailFormatError
import com.ethyllium.domain.util.Result

import java.util.regex.Pattern

class EmailState(email: String? = null) :
    TextFieldState(validator = ::isEmailValid, errorFor = ::emailValidationError) {
    init {
        email?.let {
            text = it
        }
    }
}

private fun emailValidationError(email: String): UIText {
    return when(val emailValidator = EmailValidator.isEmailValid(email)) {
        is Result.Error -> when(emailValidator.error) {
            EmailFormatError.BLANK -> UIText.StringResource(R.string.feature_register_email_blank)
            EmailFormatError.NO_AT_SYMBOL -> UIText.StringResource(R.string.feature_register_no_at_symbol)
            EmailFormatError.TOO_LONG -> UIText.StringResource(R.string.feature_register_email_too_long)
            EmailFormatError.AT_START_OR_END -> UIText.StringResource(R.string.feature_register_at_start_or_end)
            EmailFormatError.CONSECUTIVE_PERIODS -> UIText.StringResource(R.string.feature_register_consecutive_periods)
            EmailFormatError.CONTAINS_SPACES -> UIText.StringResource(R.string.feature_register_contains_spaces)
            EmailFormatError.MULTIPLE_AT_SYMBOLS -> UIText.StringResource(R.string.feature_register_multiple_at_symbols)
            EmailFormatError.LOCAL_PART_TOO_LONG -> UIText.StringResource(R.string.feature_register_local_part_too_long)
            EmailFormatError.DOMAIN_PART_TOO_LONG -> UIText.StringResource(R.string.feature_register_domain_part_too_long)
            EmailFormatError.ILLEGAL_CHARACTERS -> UIText.StringResource(R.string.feature_register_illegal_characters)
            EmailFormatError.INVALID_FORMAT -> UIText.StringResource(R.string.feature_register_invalid_format)
        }
        is Result.Success -> UIText.DynamicString("Valid Email")
    }
}

private fun isEmailValid(email: String): Boolean {
    return Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), email)
}
