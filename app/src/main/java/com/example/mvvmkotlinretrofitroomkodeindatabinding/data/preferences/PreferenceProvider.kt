package com.example.mvvmkotlinretrofitroomkodeindatabinding.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

private const val KEY_SAVED_AT = "key_saved_at"

class PreferenceProvider(private val context: Context) {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun savelastSavedAt(savedAt: String) {

        preference.edit().putString(
            KEY_SAVED_AT,
            savedAt
        ).apply()
    }

    fun getlastSavedAt(): String? {

        return preference.getString(KEY_SAVED_AT, null)
    }
}