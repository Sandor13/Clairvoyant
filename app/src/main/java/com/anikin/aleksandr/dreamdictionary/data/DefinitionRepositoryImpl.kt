package com.anikin.aleksandr.dreamdictionary.data

class DefinitionRepositoryImpl(private val definitionDataSource: DefinitionDataSource) :
    DefinitionRepository {

    override fun getDefinitionByKeyword(keyword: String): DefinitionResult {
        return definitionDataSource.getDefinitionByKeyword(keyword)
    }
}