package com.example.fitpal_android.util

import android.content.Context
import android.content.SharedPreferences

class SettingsManager (context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun toggleExecutionMode() {
        val editor = preferences.edit()
        editor.putBoolean(EXECUTION_MODE, !getExecutionMode())
        editor.apply()
    }

    fun getExecutionMode(): Boolean {
        return preferences.getBoolean(EXECUTION_MODE, DETAILED_MODE)
    }

    fun isDetailedMode(): Boolean {
        return getExecutionMode() == DETAILED_MODE
    }

    fun isSimpleMode(): Boolean {
        return getExecutionMode() == SIMPLE_MODE
    }


    companion object {
        const val PREFERENCES_NAME = "fitpal_prefs"
        const val EXECUTION_MODE = "execution_mode"
        const val DETAILED_MODE = true
        const val SIMPLE_MODE = false
    }
}