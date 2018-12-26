package com.anikin.aleksandr.dreamdictionary.data

class DefinitionRepositoryImpl(private val definitionDataSource: DefinitionDataSource) :
    DefinitionRepository {

    override fun getDefinitionByKeyword(keyword: String): DefinitionDataModel {
        return definitionDataSource.getDefinitionByKeyword(keyword)
    }
}