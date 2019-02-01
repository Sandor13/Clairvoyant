package com.anikin.aleksandr.dreamdictionary.view.splash

import com.anikin.aleksandr.dreamdictionary.data.errors.NoAuthException
import com.anikin.aleksandr.dreamdictionary.data.repository.Repository
import com.anikin.aleksandr.dreamdictionary.view.base.BaseViewModel

class SplashViewModel(/*Koin private val repository: Repository*/private val repository: Repository = Repository) : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        repository.getCurrentUser().observeForever {
            viewStateLiveData.value = if (it != null) {
                SplashViewState(isAuth = true)
            } else {
                SplashViewState(error = NoAuthException())
            }
        }
    }
}