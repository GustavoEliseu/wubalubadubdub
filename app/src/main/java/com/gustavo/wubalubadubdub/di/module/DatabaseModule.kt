package com.gustavo.wubalubadubdub.di.module

import androidx.lifecycle.ViewModelProvider
import com.gustavo.wubalubadubdub.ui.viewmodel.CharacterListViewModelFactory
import com.gustavo.wubalubadubdub.ui.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
class DatabaseModule {

    @ExperimentalCoroutinesApi
    @Provides
    fun provideCharacterListViewModelFactory(factory: CharacterListViewModelFactory): ViewModelProvider.Factory =
        factory

    @Provides
    fun provideMainViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory =
        factory
}
