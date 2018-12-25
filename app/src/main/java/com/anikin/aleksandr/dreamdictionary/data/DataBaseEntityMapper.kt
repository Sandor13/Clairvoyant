package com.anikin.aleksandr.dreamdictionary.data

import com.anikin.aleksandr.dreamdictionary.data.room.DreamDictionaryEntity

object DataBaseEntityMapper {

    fun map(entity: DreamDictionaryEntity?): DefinitionResult = DefinitionResult.Success(Definition("", ""))
}