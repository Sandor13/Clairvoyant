package com.anikin.aleksandr.dreamdictionary.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [(DreamDictionaryEntity::class)], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun dreamDictionaryDAO(): DreamDictionaryDAO
}
