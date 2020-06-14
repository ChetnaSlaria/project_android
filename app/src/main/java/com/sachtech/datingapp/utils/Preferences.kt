package com.sachtech.datingapp.utils

import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import com.sachtech.datingapp.app.DatingApp
import com.sachtech.datingapp.extensions.isNull

object Preferences {


    val prefs = DatingApp.application?.applicationContext!!.getSharedPreferences(
        "DatingYOOOOO",
        MODE_PRIVATE
    )

    fun setPref(key: String, value: Any?) {

        val editor = prefs.edit()
        when (value) {
            is String -> editor?.putString(key, value)
            is Int -> editor?.putInt(key, value)
            is Long -> editor?.putLong(key, value)
            is Boolean -> editor?.putBoolean(key, value)
            is Float -> editor?.putFloat(key, value)
        }
        editor?.apply()
    }

    inline fun <reified T : Any> get(key: String, defaultValue: T?): T? {
        return when (T::class) {
            String::class -> prefs?.getString(key, defaultValue as? String) as T?
            Int::class -> prefs?.getInt(key, defaultValue as? Int ?: 1) as T?
            Boolean::class -> prefs?.getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> prefs?.getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> prefs?.getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    fun clearPref() {
        val editor = prefs.edit()
        editor?.clear()?.apply()
    }

    fun clearPrefKey(key: String) {
        val editor = prefs.edit()
        editor.remove(key)
        editor?.apply()
    }

    inline fun <reified T> setprefObject(key: String, obj: T?) {
        if (obj.isNull())
            clearPrefKey(key)
        else
            setPref(key, Gson().toJson(obj))
    }

    inline fun <reified T> getprefObject(key: String): T? {
        return Gson().fromJson(get(key, ""), T::class.java)
    }
}