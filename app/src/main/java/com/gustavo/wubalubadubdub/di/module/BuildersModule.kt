package com.gustavo.wubalubadubdub.di.module

import com.gustavo.wubalubadubdub.ui.Activity.CharacterDetailsActivity
import com.gustavo.wubalubadubdub.ui.Activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeCharacterDetailsActivity() : CharacterDetailsActivity
}