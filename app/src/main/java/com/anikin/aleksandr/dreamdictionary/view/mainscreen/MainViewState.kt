package com.anikin.aleksandr.dreamdictionary.view.mainscreen

import com.anikin.aleksandr.dreamdictionary.data.model.Note
import com.anikin.aleksandr.dreamdictionary.view.base.BaseViewState

class MainViewState(notes: List<Note>? = null, error: Throwable? = null) : BaseViewState<List<Note>?>(notes, error)
