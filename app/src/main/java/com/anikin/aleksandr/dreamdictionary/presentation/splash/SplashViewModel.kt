package com.anikin.aleksandr.dreamdictionary.presentation.splash

import com.anikin.aleksandr.dreamdictionary.data.datasource.DataSourceRemote
import com.anikin.aleksandr.dreamdictionary.data.errors.NoAuthException
import com.anikin.aleksandr.dreamdictionary.data.repository.RepositoryImpl
import com.anikin.aleksandr.dreamdictionary.presentation.base.BaseViewModel

class SplashViewModel(/*Koin private val repository: Repository*/private val repository: RepositoryImpl = RepositoryImpl(
    DataSourceRemote()
)) :
    BaseViewModel<Boolean?, SplashViewState>() {

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