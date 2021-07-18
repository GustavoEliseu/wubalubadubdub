package com.gustavo.wubalubadubdub.di.component

import com.gustavo.wubalubadubdub.MyApplication
import com.gustavo.wubalubadubdub.di.module.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, NetworkModule::class,BuildersModule::class, FragmentModule::class,  DatabaseModule::class, RepositoryModule::class]
)
interface MainComponentInjector {
    fun inject(app: MyApplication)
}