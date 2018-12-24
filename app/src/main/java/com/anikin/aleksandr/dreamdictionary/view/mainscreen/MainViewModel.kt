package com.anikin.aleksandr.dreamdictionary.view.mainscreen

import android.arch.lifecycle.Observer
import com.anikin.aleksandr.dreamdictionary.data.model.Note
import com.anikin.aleksandr.dreamdictionary.data.model.NoteResult
import com.anikin.aleksandr.dreamdictionary.data.model.NoteResult.Error
import com.anikin.aleksandr.dreamdictionary.data.model.NoteResult.Success
import com.anikin.aleksandr.dreamdictionary.data.repository.Repository
import com.anikin.aleksandr.dreamdictionary.view.base.BaseViewModel

class MainViewModel(repository: Repository) : BaseViewModel<List<Note>?, MainViewState>() {

    private val repositoryNotes = repository.getNotes()
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
        repositoryNotes.observeForever(notesObserver)
    }

    override fun onCleared() {
        repositoryNotes.removeObserver(notesObserver)
    }
}


