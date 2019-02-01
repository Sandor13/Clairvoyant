package com.anikin.aleksandr.dreamdictionary.data.datasource

import com.anikin.aleksandr.dreamdictionary.data.models.Note
import com.anikin.aleksandr.dreamdictionary.data.provider.FireStoreProvider
import com.anikin.aleksandr.dreamdictionary.data.provider.RemoteDataProvider

class DataSourceRemote(private val remoteProvider: RemoteDataProvider = FireStoreProvider()) : DataSource {

    override fun getNotes() = remoteProvider.subscribeToAllNotes()

    override fun saveNote(note: Note) = remoteProvider.saveNote(note)

    override fun getNoteById(id: String) = remoteProvider.getNoteById(id)

    override fun getCurrentUser() = remoteProvider.getCurrentUser()

    override fun deleteNote(noteId: String) = remoteProvider.deleteNote(noteId)
}