package com.anikin.aleksandr.dreamdictionary.data.provider

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.anikin.aleksandr.dreamdictionary.data.errors.NoAuthException
import com.anikin.aleksandr.dreamdictionary.data.model.NoteResult
import com.anikin.aleksandr.dreamdictionary.data.model.User
import com.anikin.aleksandr.dreamdictionary.data.models.Note
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FireStoreProvider
/*
Koin
(
   private val firebaseAuth: FirebaseAuth,
   private val db: FirebaseFirestore
)*/

    : RemoteDataProvider {

    private val NOTES_COLLECTION = "notes"
    private val USERS_COLLECTION = "users"

    private val db = FirebaseFirestore.getInstance()
    private val currentUser
        get() = FirebaseAuth.getInstance().currentUser

    private fun getUserNotesCollection() = currentUser?.let {
        db.collection(USERS_COLLECTION).document(it.uid).collection(NOTES_COLLECTION)
    } ?: throw NoAuthException()

    override fun subscribeToAllNotes(): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()
        try {
            getUserNotesCollection()
                .addSnapshotListener { snapshot, e ->
                    e?.let {
                        throw it
                    } ?: snapshot?.let {
                        result.value = NoteResult.Success(snapshot.documents.map { it.toObject(Note::class.java) })
                    }
                }
        } catch (e: Throwable) {
            result.value = NoteResult.Error(e)
        }
        return result
    }

    override fun getNoteById(id: String): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()
        try {
            getUserNotesCollection()
                .document(id)
                .get()
                .addOnSuccessListener {
                    result.value = NoteResult.Success(it.toObject(Note::class.java))
                }
                .addOnFailureListener {
                    throw it
                }
        } catch (e: Throwable) {
            result.value = NoteResult.Error(e)
        }
        return result
    }

    override fun saveNote(note: Note): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()
        try {
            getUserNotesCollection()
                .document(note.id)
                .set(note)
                .addOnSuccessListener {
                    result.value = NoteResult.Success(note)
                }
                .addOnFailureListener {
                    OnFailureListener {
                        throw it
                    }
                }
        } catch (e: Throwable) {
            result.value = NoteResult.Error(e)
        }
        return result
    }

    override fun getCurrentUser(): LiveData<User?> =
        MutableLiveData<User?>().apply {
            value = currentUser?.let {
                User(
                    it.displayName ?: "",
                    it.email ?: ""
                )
            }
        }

    override fun deleteNote(noteId: String): LiveData<NoteResult> =
        MutableLiveData<NoteResult>().apply {
            getUserNotesCollection().document(noteId).delete()
                .addOnSuccessListener {
                    value = NoteResult.Success(null)
                }.addOnFailureListener {
                    value = NoteResult.Error(it)
                }
        }
}