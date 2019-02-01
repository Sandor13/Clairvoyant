package com.anikin.aleksandr.dreamdictionary.presentation.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import com.anikin.aleksandr.dreamdictionary.data.model.NoteResult
import com.anikin.aleksandr.dreamdictionary.data.model.NoteResult.Error
import com.anikin.aleksandr.dreamdictionary.data.model.NoteResult.Success
import com.anikin.aleksandr.dreamdictionary.data.models.Note
import com.anikin.aleksandr.dreamdictionary.domain.interactors.DefinitionInteractor
import com.anikin.aleksandr.dreamdictionary.domain.interactors.DefinitionInteractorImpl
import com.anikin.aleksandr.dreamdictionary.presentation.base.BaseViewModel

class MainViewModel(
    private val definitionInteractor: DefinitionInteractor = DefinitionInteractorImpl()
) : BaseViewModel<List<Note>?, MainViewState>() {

    private lateinit var repositoryNotes: LiveData<NoteResult>
    private val notesObserver =
        Observer<NoteResult> { t ->
            if (t == null) return@Observer
            when (t) {
                is Success<*> -> {
                    viewStateLiveData.value = MainViewState(notes = t.data as? List<Note>)
                }
                is Error -> {
                    viewStateLiveData.value = MainViewState(error = t.error)
                }
            }
        }

    init {
        viewStateLiveData.value = MainViewState()
    }

    fun getDefinition(isOnline: Boolean) {
        repositoryNotes = definitionInteractor.getDefinition(isOnline)
        repositoryNotes.observeForever(notesObserver)
    }

    override fun onCleared() {
        repositoryNotes.removeObserver(notesObserver)
    }
}