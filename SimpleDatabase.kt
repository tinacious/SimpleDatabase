package [com.mydomain.myapp]

import android.content.SharedPreferences
import android.text.TextUtils
import com.google.gson.Gson

/**
 * A simple database class written in Kotlin.
 * Github repository: https://github.com/tinacious/simpledatabase
 *
 * @author Tina Holly, info@tinaciousdesign.com
 * @version 1.1.0
 * @since 2021-03-08
 */
interface SimpleDatabase {
    fun getString(key: String, defaultValue: String?): String?
    fun putString(key: String, value: String)
    fun getInt(key: String, defaultValue: Int): Int
    fun putInt(key: String, value: Int)
    fun getBoolean(key: String, defaultValue: Boolean): Boolean
    fun putBoolean(key: String, value: Boolean)
    fun <T> putObject(key: String, value: T)
    fun <T> getObject(key: String, mClass: Class<T>): T?
    fun <T> getListObject(key: String, mClass: Class<T>): ArrayList<T>
    fun <T> putListObject(key: String, objArray: ArrayList<T>)
    fun remove(key: String)
}

class SimpleDatabaseImpl(sharedPreferences: SharedPreferences) : SimpleDatabase {
    private val gson = Gson()
    private val preferences = sharedPreferences

    /**
     * Strings
     */

    override fun getString(key: String, defaultValue: String?): String? =
        preferences.getString(key, defaultValue) ?: defaultValue

    override fun putString(key: String, value: String) {
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
     * Objects
     */

    override fun <T> putObject(key: String, value: T) {
        val serialized = gson.toJson(value) as String
        preferences.edit().putString(key, serialized).apply()
    }

    override fun <T> getObject(key: String, mClass: Class<T>): T? {
        val stringObject = preferences.getString(key, null) ?: return null
        return gson.fromJson(stringObject, mClass)
    }

    /**
     * Object lists
     */

    override fun <T> getListObject(key: String, mClass: Class<T>): ArrayList<T> =
        getListString(key)
            .map { gson.fromJson(it, mClass) } as ArrayList<T>

    override fun <T> putListObject(key: String, objArray: ArrayList<T>) {
        val result = objArray.map { gson.toJson(it) } as ArrayList<String>
        putListString(key, result)
    }

    /**
     * Int
     */

    override fun getInt(key: String, defaultValue: Int): Int =
        preferences.getInt(key, defaultValue)

    override fun putInt(key: String, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }

    /**
     * Boolean
     */
    override fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        preferences.getBoolean(key, defaultValue)

    override fun putBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    /**
     * All types
     */

    override fun remove(key: String) {
        preferences.edit().remove(key).apply()
    }
}
