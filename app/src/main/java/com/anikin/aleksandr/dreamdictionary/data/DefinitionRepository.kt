package com.anikin.aleksandr.dreamdictionary.data

interface DefinitionRepository {

    fun getDefinitionByKeyword(keyword: String): DefinitionDataModel
}