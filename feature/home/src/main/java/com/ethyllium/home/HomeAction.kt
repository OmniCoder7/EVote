package com.ethyllium.home

sealed interface HomeAction {
    data class CopyToClipboard(val label: String, val text: String) : HomeAction
}