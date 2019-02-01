package com.anikin.aleksandr.dreamdictionary.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * @author Aleksandr Anikin
 */
@Database(entities = arrayOf(DictionaryEntity::class), version = 1, exportSchema = false)
abstract class DictionaryDataBase : RoomDatabase() {
    abstract val dictionaryDao: DictionaryDao
}
