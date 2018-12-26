package com.anikin.aleksandr.dreamdictionary.data

import android.arch.lifecycle.LiveData

interface DefinitionInteractor {

    fun getDefinitionByKeyword(keyword: String): LiveData<DefinitionDataModel>
}