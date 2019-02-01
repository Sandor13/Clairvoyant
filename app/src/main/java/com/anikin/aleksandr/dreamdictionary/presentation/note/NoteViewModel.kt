package com.anikin.aleksandr.dreamdictionary.view.managenote

import android.arch.lifecycle.Observer
import com.anikin.aleksandr.dreamdictionary.data.datasource.DataSourceRemote
import com.anikin.aleksandr.dreamdictionary.data.model.NoteResult
import com.anikin.aleksandr.dreamdictionary.data.model.NoteResult.Error
import com.anikin.aleksandr.dreamdictionary.data.model.NoteResult.Success
import com.anikin.aleksandr.dreamdictionary.data.models.Note
import com.anikin.aleksandr.dreamdictionary.data.repository.RepositoryImpl
import com.anikin.aleksandr.dreamdictionary.presentation.base.BaseViewModel
import com.anikin.aleksandr.dreamdictionary.view.managenote.NoteViewState.Data

class NoteViewModel(/*//Koin private val repository: Repository*/private val repository: RepositoryImpl = RepositoryImpl(
    DataSourceRemote()
)) :
    BaseViewModel<Data, NoteViewState>() {

    private var pendingNote: Note? = null
    private val currentNote: Note?
        get() = viewStateLiveData.value?.data?.note

    fun saveChanges(note: Note) {
        viewStateLiveData.value = NoteViewState(Data(note = note))
    }

    fun loadNote(noteId: String) {
        repository.getNoteById(noteId).observeForever(Observer<NoteResult> { t ->
            if (t == null) return@Observer
            when (t) {
                is Success<*> ->
                    viewStateLiveData.value = NoteViewState(Data(note = t.data as? Note))
                is Error ->
                    viewStateLiveData.value = NoteViewState(error = t.error)
            }
        })
    }

    fun deleteNote() {
        currentNote?.let {
            repository.deleteNote(it.id).observeForever { t ->
                t?.let {
                    viewStateLiveData.value = when (it) {
                        is Success<*> -> NoteViewState(Data(isDeleted = true))
                        is Error -> NoteViewState(error = it.error)
                    }
                }
            }
        }
    }

    override fun onCleared() {
        currentNote?.let { repository.saveNote(it) }
    }
}