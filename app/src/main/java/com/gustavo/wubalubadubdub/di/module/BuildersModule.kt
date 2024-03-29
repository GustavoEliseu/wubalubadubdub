package com.gustavo.wubalubadubdub.di.module

import com.gustavo.wubalubadubdub.ui.activity.CharacterDetailsActivity
import com.gustavo.wubalubadubdub.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeCharacterDetailsActivity() : CharacterDetailsActivity
}