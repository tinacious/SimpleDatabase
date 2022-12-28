package com.tinaciousdesign.simpledatabaseexample

import android.content.Context
import com.tinaciousdesign.simpledatabase.SimpleDatabase
import com.tinaciousdesign.simpledatabase.SimpleDatabaseImpl

/**
 * Simple key value storage using SimpleDatabase
 */
class KeyValueStorage private constructor(private val delegate: SimpleDatabase) :
    SimpleDatabase by delegate {
    companion object {
        @JvmStatic
        fun getInstance(context: Context): KeyValueStorage {
            val sharedPreferences = context.getSharedPreferences(
                "${context.packageName}_preferences",
                Context.MODE_PRIVATE
            )
            val simpleDatabaseImpl = SimpleDatabaseImpl(sharedPreferences)

            return KeyValueStorage(simpleDatabaseImpl)
        }
    }
}