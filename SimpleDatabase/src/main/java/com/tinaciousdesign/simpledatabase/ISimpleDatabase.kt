package com.tinaciousdesign.simpledatabase

interface ISimpleDatabase {
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