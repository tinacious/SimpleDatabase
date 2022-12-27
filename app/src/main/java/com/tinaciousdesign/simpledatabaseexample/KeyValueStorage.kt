package com.tinaciousdesign.simpledatabaseexample

import android.content.Context
import com.tinaciousdesign.simpledatabase.ISimpleDatabase
import com.tinaciousdesign.simpledatabase.SimpleDatabase

class KeyValueStorage private constructor(private val delegate: ISimpleDatabase) :
    ISimpleDatabase by delegate {
    companion object {
        @JvmStatic
        fun getInstance(context: Context): KeyValueStorage {
            val sharedPreferences = context.getSharedPreferences(
                "${context.packageName}_preferences",
                Context.MODE_PRIVATE
            )
            val simpleDatabase = SimpleDatabase(sharedPreferences)

            return KeyValueStorage(simpleDatabase)
        }
    }
}