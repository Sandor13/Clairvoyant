package com.anikin.aleksandr.dreamdictionary.data

import com.anikin.aleksandr.dreamdictionary.data.room.AppDataBase

class DataBaseDataSource(private val keyword: String, private val db: AppDataBase) : DefinitionDataSource {

    override fun getDefinitionByKeyword(keyword: String): DefinitionResult {
        return DataBaseEntityMapper.map(db.dreamDictionaryDAO().getDefinitionByKeyword(keyword))
    }
}