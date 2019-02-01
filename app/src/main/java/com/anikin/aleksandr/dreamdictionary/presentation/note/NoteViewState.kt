package com.anikin.aleksandr.dreamdictionary.view.managenote

import com.anikin.aleksandr.dreamdictionary.data.models.Note
import com.anikin.aleksandr.dreamdictionary.view.base.BaseViewState

class NoteViewState(
    data: Data = Data(),
    error: Throwable? = null
) : BaseViewState<NoteViewState.Data>(data, error) {

    data class Data(val isDeleted: Boolean = false, val note: Note? = null)
}