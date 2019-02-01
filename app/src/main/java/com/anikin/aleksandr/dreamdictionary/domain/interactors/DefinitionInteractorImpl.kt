package com.anikin.aleksandr.dreamdictionary.domain.interactors

import android.arch.lifecycle.LiveData
import com.anikin.aleksandr.dreamdictionary.data.datasource.DataSourceLocal
import com.anikin.aleksandr.dreamdictionary.data.datasource.DataSourceRemote
import com.anikin.aleksandr.dreamdictionary.data.model.NoteResult
import com.anikin.aleksandr.dreamdictionary.data.repository.Repository
import com.anikin.aleksandr.dreamdictionary.data.repository.RepositoryImpl

class DefinitionInteractorImpl() : DefinitionInteractor {

    private lateinit var repository: Repository

    override fun getDefinition(isOnline: Boolean): LiveData<NoteResult> {
        if (isOnline) {
            repository = RepositoryImpl(DataSourceRemote())
        } else {
            repository = RepositoryImpl(DataSourceLocal())
        }
        return repository.getNotes()
    }
}