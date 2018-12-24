package com.anikin.aleksandr.dreamdictionary.di

import com.anikin.aleksandr.dreamdictionary.data.provider.FireStoreProvider
import com.anikin.aleksandr.dreamdictionary.data.provider.RemoteDataProvider
import com.anikin.aleksandr.dreamdictionary.data.repository.Repository
import com.anikin.aleksandr.dreamdictionary.view.mainscreen.MainViewModel
import com.anikin.aleksandr.dreamdictionary.view.managenote.NoteViewModel
import com.anikin.aleksandr.dreamdictionary.view.splash.SplashViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FireStoreProvider(get(), get()) } bind RemoteDataProvider::class
    single { Repository(get()) }
}

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val noteModule = module {
    viewModel { NoteViewModel(get()) }
}