package com.ethyllium.domain.error

import com.ethyllium.domain.error.Error

enum class PasswordError: Error {
    BLANK,
    TOO_SHORT,
    NO_LOWERCASE,
    NO_UPPERCASE,
    NO_DIGIT,
    NO_SPECIAL_CHAR,
}