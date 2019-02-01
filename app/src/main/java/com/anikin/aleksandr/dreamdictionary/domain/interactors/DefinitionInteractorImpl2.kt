package com.anikin.aleksandr.dreamdictionary.domain.interactors

import com.anikin.aleksandr.dreamdictionary.data.repository.Repository2

class DefinitionInteractorImpl2(val repository: Repository2) : DefinitionInteractor2 {

    override fun getDefinition(): String {
        return repository.getDefinition()
    }
}