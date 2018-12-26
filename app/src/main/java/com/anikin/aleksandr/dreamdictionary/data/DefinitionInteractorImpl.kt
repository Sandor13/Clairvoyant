package com.anikin.aleksandr.dreamdictionary.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class DefinitionInteractorImpl(private val definitionRepository: DefinitionRepository) :
    DefinitionInteractor {

    override fun getDefinitionByKeyword(keyword: String): LiveData<DefinitionDataModel> {
        val result = MutableLiveData<DefinitionDataModel>()
        result.value = definitionRepository.getDefinitionByKeyword(keyword)
        return result
    }
}