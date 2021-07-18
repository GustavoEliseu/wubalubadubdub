package com.gustavo.wubalubadubdub.di.module

import com.gustavo.wubalubadubdub.ui.fragments.characters.CharacterListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeCharacterListFragment(): CharacterListFragment
}