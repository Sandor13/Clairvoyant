package com.anikin.aleksandr.dreamdictionary.data

sealed class DefinitionDataModel {

    data class Success<out T>(val definition: T) : DefinitionDataModel()
    data class Error(val error: Throwable) : DefinitionDataModel()
}