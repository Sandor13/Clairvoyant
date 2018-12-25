package com.anikin.aleksandr.dreamdictionary.data

sealed class DefinitionResult {

    data class Success<out T>(val definition: T) : DefinitionResult()
    data class Error(val error: Throwable) : DefinitionResult()
}