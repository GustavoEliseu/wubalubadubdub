package com.gustavo.wubalubadubdub.di.module

import com.gustavo.wubalubadubdub.ui.fragments.characters.CharacterItemFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeCharacterItemFragment(): CharacterItemFragment
}