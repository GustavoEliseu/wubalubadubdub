package com.gustavo.wubalubadubdub.di.module

import androidx.lifecycle.ViewModelProvider
import com.gustavo.wubalubadubdub.ui.viewmodel.CharacterListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideCharacterListViewModelFactory(factory: CharacterListViewModelFactory): ViewModelProvider.Factory =
        factory
}