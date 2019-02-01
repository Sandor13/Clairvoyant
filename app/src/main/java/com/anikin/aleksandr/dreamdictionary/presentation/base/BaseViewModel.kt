package com.anikin.aleksandr.dreamdictionary.presentation.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.anikin.aleksandr.dreamdictionary.view.base.BaseViewState

abstract class BaseViewModel<T, S : BaseViewState<T>> : ViewModel() {

    protected open val viewStateLiveData = MutableLiveData<S>()
    open fun getViewState(): LiveData<S> = viewStateLiveData
}
