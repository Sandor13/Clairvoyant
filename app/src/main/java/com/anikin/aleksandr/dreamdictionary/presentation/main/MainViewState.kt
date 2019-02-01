package com.anikin.aleksandr.dreamdictionary.presentation.main

import com.anikin.aleksandr.dreamdictionary.data.models.Note
import com.anikin.aleksandr.dreamdictionary.view.base.BaseViewState

class MainViewState(notes: List<Note>? = null, error: Throwable? = null) : BaseViewState<List<Note>?>(notes, error)
