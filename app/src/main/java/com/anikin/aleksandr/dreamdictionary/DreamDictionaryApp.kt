package com.anikin.aleksandr.dreamdictionary

import android.support.multidex.MultiDexApplication
import com.anikin.aleksandr.dreamdictionary.data.db.DictionaryEntity
import com.anikin.aleksandr.dreamdictionary.data.models.Dictionary
import com.anikin.aleksandr.dreamdictionary.data.models.DictionaryJson
import com.google.common.collect.ImmutableList
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStreamReader

class DreamDictionaryApp : MultiDexApplication() {

    companion object {
        private const val DB_NAME = "Dictionary"
        private const val DICTIONARY_JSON = "dictionary.json"
        private const val KEYWORD = "A"
    }

    //private var dictionaryDB: DictionaryDataBase

    private val dataFromAssets: DictionaryJson? by lazy {
        val gson = Gson()
        try {
            val br = InputStreamReader(assets.open(DICTIONARY_JSON))
            gson.fromJson(br, DictionaryJson::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }


    override fun onCreate() {
        super.onCreate()
        //val dictionaryDB = Room.databaseBuilder(this, DictionaryDataBase::class.java, DB_NAME).allowMainThreadQueries().build()
        //val dictionaryDao = dictionaryDB.dictionaryDao
       /* if (dictionaryDao.getDescriptionByKeyword(KEYWORD) == null) {
            dictionaryDao.insertAll(mapDictionaryToEntity(getDictionaryFromJson(dataFromAssets)))
        }*/
        //startKoin(this, listOf(appModule, splashModule, mainModule, noteModule))
    }

    private fun mapDictionaryToEntity(dictionaryFromJson: ImmutableList<Dictionary>): ImmutableList<DictionaryEntity> {
        val entities = emptyList<DictionaryEntity>()
        dictionaryFromJson.map { entities }
        return entities as ImmutableList<DictionaryEntity>
    }

    private fun getDictionaryFromJson(dataFromAssets: DictionaryJson?): ImmutableList<Dictionary> =
        dataFromAssets?.dictionary ?: emptyList<Dictionary>() as ImmutableList<Dictionary>
}

