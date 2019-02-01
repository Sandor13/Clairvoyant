package com.anikin.aleksandr.dreamdictionary

import android.app.Application

class DreamDictionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //startKoin(this, listOf(appModule, splashModule, mainModule, noteModule))
    }
}
