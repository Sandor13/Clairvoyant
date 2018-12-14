package com.anikin.aleksandr.dreamdictionary.ui.mainscreen

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.anikin.aleksandr.dreamdictionary.data.repository.Repository

class MainViewModel : ViewModel() {

    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    init {
        viewStateLiveData.value = MainViewState(Repository.notes)
    }

    fun getViewState(): LiveData<MainViewState> = viewStateLiveData
}
