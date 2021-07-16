package com.gustavo.wubalubadubdub.di.module

import com.gustavo.wubalubadubdub.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

}