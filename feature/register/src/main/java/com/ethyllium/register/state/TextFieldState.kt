package com.ethyllium.register.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class TextFieldState(
    private val validator: (String) -> Boolean = { true },
    private val errorFor: (String) -> UIText = { UIText.DynamicString("") }
) {
    var text: String by mutableStateOf("")
    var isFocusedDirty: Boolean by mutableStateOf(false)
    private var isFocused: Boolean by mutableStateOf(false)
    private var displayErrors: Boolean by mutableStateOf(false)

    open val isValid: Boolean
        get() = validator(text)

    fun onFocusChange(focused: Boolean) {
        isFocused = focused
        if (focused) isFocusedDirty = true
    }

    fun enableShowErrors() {
        if (isFocusedDirty) {
            displayErrors = true
        }
    }

    fun showErrors() = !isValid && displayErrors

    open fun getError(): UIText? {
        return if (showErrors()) {
            errorFor(text)
        } else {
            null
        }
    }
}

