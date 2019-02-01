package com.anikin.aleksandr.dreamdictionary.data.repository

import com.anikin.aleksandr.dreamdictionary.data.datasource.DataSource
import com.anikin.aleksandr.dreamdictionary.data.models.Note


class RepositoryImpl(private val datasource : DataSource) : Repository {

    //private val remoteProvider: RemoteDataProvider = FireStoreProvider()

    override fun getNotes() = datasource.getNotes()

    override fun saveNote(note: Note) = datasource.saveNote(note)

    override fun getNoteById(id: String) = datasource.getNoteById(id)

    override fun getCurrentUser() = datasource.getCurrentUser()

    override fun deleteNote(noteId: String) = datasource.deleteNote(noteId)
}
/*
//Koin
class Repository(private val remoteProvider: RemoteDataProvider) {

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    fun getCurrentUser() = remoteProvider.getCurrentUser()
    fun deleteNote(noteId: String) = remoteProvider.deleteNote(noteId)
}*/
