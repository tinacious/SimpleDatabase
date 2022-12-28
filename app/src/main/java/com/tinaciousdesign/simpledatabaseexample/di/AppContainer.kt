package com.tinaciousdesign.simpledatabaseexample.di

import android.content.Context
import com.tinaciousdesign.simpledatabaseexample.KeyValueStorage

class AppContainer constructor(private val context: Context) {
    val keyValueStorage: KeyValueStorage = KeyValueStorage.getInstance(context)
}