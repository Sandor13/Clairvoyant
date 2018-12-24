package com.anikin.aleksandr.dreamdictionary

import android.support.multidex.MultiDexApplication
import com.anikin.aleksandr.dreamdictionary.di.appModule
import com.anikin.aleksandr.dreamdictionary.di.mainModule
import com.anikin.aleksandr.dreamdictionary.di.noteModule
import com.anikin.aleksandr.dreamdictionary.di.splashModule
import org.koin.android.ext.android.startKoin

class DreamDictionaryApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, splashModule, mainModule, noteModule))
    }
}
