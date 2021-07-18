package com.gustavo.wubalubadubdub

import android.app.Application
import com.gustavo.wubalubadubdub.di.component.DaggerMainComponentInjector
import com.gustavo.wubalubadubdub.di.module.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import javax.inject.Inject

class MyApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>

    override fun onCreate(){
        super.onCreate()
        // RxJava errorHandler
        RxJavaPlugins.setErrorHandler { Timber.d(it) }
        // Dagger injection
        DaggerMainComponentInjector.builder()
            .build().inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}