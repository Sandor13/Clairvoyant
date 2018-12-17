package com.anikin.aleksandr.dreamdictionary.ui.managenote

import android.arch.lifecycle.ViewModel
import com.anikin.aleksandr.dreamdictionary.data.model.Note
import com.anikin.aleksandr.dreamdictionary.data.repository.Repository

class NoteViewModel(val repository: Repository = Repository) : ViewModel() {

    private var pendingNote: Note? = null

    fun saveChanges(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        if (pendingNote != null) {
            repository.saveNote(pendingNote!!)
        }
    }
}