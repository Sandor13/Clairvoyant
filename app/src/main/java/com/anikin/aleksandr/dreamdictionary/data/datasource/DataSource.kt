package com.anikin.aleksandr.dreamdictionary.data.datasource

import android.arch.lifecycle.LiveData
import com.anikin.aleksandr.dreamdictionary.data.model.NoteResult
import com.anikin.aleksandr.dreamdictionary.data.model.User
import com.anikin.aleksandr.dreamdictionary.data.models.Note

interface DataSource {

    fun getNotes(): LiveData<NoteResult>

    fun saveNote(note: Note): LiveData<NoteResult>

    fun getNoteById(id: String): LiveData<NoteResult>

    fun getCurrentUser(): LiveData<User?>

    fun deleteNote(noteId: String): LiveData<NoteResult>
}