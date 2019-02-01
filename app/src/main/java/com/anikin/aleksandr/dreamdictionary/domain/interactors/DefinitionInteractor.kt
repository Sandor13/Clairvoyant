package com.anikin.aleksandr.dreamdictionary.domain.interactors

import android.arch.lifecycle.LiveData
import com.anikin.aleksandr.dreamdictionary.data.model.NoteResult

interface DefinitionInteractor {

    fun getDefinition(isOnline: Boolean): LiveData<NoteResult>
}