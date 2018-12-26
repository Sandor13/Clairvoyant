package com.anikin.aleksandr.dreamdictionary.data

interface DefinitionDataSource {

    fun getDefinitionByKeyword(keyword: String): DefinitionDataModel
}