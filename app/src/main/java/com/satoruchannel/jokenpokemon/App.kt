package com.satoruchannel.jokenpokemon

import android.app.Application
import com.activeandroid.ActiveAndroid
import com.facebook.stetho.Stetho

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        ActiveAndroid.initialize(this)
    }
}
