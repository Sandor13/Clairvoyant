package com.anikin.aleksandr.dreamdictionary.data

import com.anikin.aleksandr.dreamdictionary.data.room.DreamDictionaryDAO

class DefinitionDataSourceDataBase(private val keyword: String, private val dreamDictionaryDAO: DreamDictionaryDAO) :
    DefinitionDataSource {

    override fun getDefinitionByKeyword(keyword: String): DefinitionDataModel {
        return DataBaseEntityMapper.map(dreamDictionaryDAO.getDefinitionByKeyword(keyword))
    }
}