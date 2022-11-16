package com.example.fitpal_android.ui.screens.appContent.settings

data class SettingsState(
    val executionMode: Boolean = DETAILED_MODE
) {

    companion object {
        const val DETAILED_MODE = true
        const val SIMPLE_MODE = false
    }
}