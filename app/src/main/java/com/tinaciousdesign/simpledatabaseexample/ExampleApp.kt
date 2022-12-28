package com.tinaciousdesign.simpledatabaseexample

import android.app.Application
import com.tinaciousdesign.simpledatabaseexample.di.AppContainer

class ExampleApp : Application() {
    lateinit var dependencies: AppContainer

    override fun onCreate() {
        super.onCreate()
        this.dependencies = AppContainer(this)
    }
}