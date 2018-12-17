package com.anikin.aleksandr.dreamdictionary.ui.mainscreen

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.anikin.aleksandr.dreamdictionary.data.repository.Repository

class MainViewModel(repository: Repository = Repository) : ViewModel() {

    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    init {
        repository.getNotesLiveData().observeForever {
            viewStateLiveData.value = viewStateLiveData.value?.copy(notes = it!!) ?: MainViewState(it!!)
        }
    }

    fun getViewState(): LiveData<MainViewState> = viewStateLiveData
}
