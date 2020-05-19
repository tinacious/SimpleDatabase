package com.tinaciousdesign.cannedreplies.services

import android.content.SharedPreferences
import android.text.TextUtils
import com.google.gson.Gson


class SimpleDatabase(sharedPreferences: SharedPreferences) {
    private val gson = Gson()
    private val preferences = sharedPreferences


    /**
     * Strings
     */

    fun getString(key: String): String =
        preferences.getString(key, "") ?: ""

    fun putString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }


    /**
     * String lists
     */

    fun getListString(key: String): ArrayList<String> =
        ArrayList(
            listOf(
                *TextUtils.split(preferences.getString(key, ""), "‚‗‚")
            )
        )

    fun putListString(key: String, stringList: ArrayList<String>) =
        preferences.edit()
            .putString(key, TextUtils.join("‚‗‚", stringList.toTypedArray()))
            .apply()


    /**
     * Object lists
     */

    fun <T> getListObject(key: String, mClass: Class<T>): ArrayList<T> =
        getListString(key)
            .map { gson.fromJson(it, mClass) } as ArrayList<T>

    fun <T> putListObject(key: String, objArray: ArrayList<T>) {
        val result = objArray.map { gson.toJson(it) } as ArrayList<String>
        putListString(key, result)
    }


    /**
     * Int
     */

    fun getInt(key: String): Int = preferences.getInt(key, 0)

    fun putInt(key: String, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }


    /**
     * All types
     */

    fun remove(key: String) {
        preferences.edit().remove(key).apply()
    }
}
