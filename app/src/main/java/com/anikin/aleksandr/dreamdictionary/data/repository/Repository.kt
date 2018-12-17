package com.anikin.aleksandr.dreamdictionary.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.anikin.aleksandr.dreamdictionary.data.model.Color
import com.anikin.aleksandr.dreamdictionary.data.model.Note
import java.util.*

object Repository {

    private val notesLiveData = MutableLiveData<List<Note>>()

    private val notes: MutableList<Note> = mutableListOf(
        Note(
            id = UUID.randomUUID().toString(),
            title = "Моя первая заметка",
            description = "Kotlin очень краткий, но при этом выразительный язык",
            color = Color.WHITE
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "Моя первая заметка",
            description = "Kotlin очень краткий, но при этом выразительный язык",
            color = Color.BLUE
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "Моя первая заметка",
            description = "Kotlin очень краткий, но при этом выразительный язык",
            color = Color.GREEN
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "Моя первая заметка",
            description = "Kotlin очень краткий, но при этом выразительный язык",
            color = Color.PINK
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "Моя первая заметка",
            description = "Kotlin очень краткий, но при этом выразительный язык",
            color = Color.RED
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "Моя первая заметка",
            description = "Kotlin очень краткий, но при этом выразительный язык",
            color = Color.YELLOW
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "Моя первая заметка",
            description = "Kotlin очень краткий, но при этом выразительный язык",
            color = Color.VIOLET
        )
    )

    init {
        notesLiveData.value = notes
    }

    fun getNotesLiveData(): LiveData<List<Note>> {
        return notesLiveData
    }

    fun saveNote(note: Note) {
        addOrReplace(note)
        notesLiveData.value = notes
    }

    private fun addOrReplace(note: Note) {
        val notesSize = notes.size
        for (i in 0 until notesSize) {
            if (notes[i] == note) {
                notes.set(i, note)
                return
            }
        }
        notes.add(note)
    }
}