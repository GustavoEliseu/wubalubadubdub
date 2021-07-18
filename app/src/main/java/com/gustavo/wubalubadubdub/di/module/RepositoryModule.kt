package com.gustavo.wubalubadubdub.di.module

import com.gustavo.wubalubadubdub.source.repository.CharacterRepository
import com.gustavo.wubalubadubdub.source.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl
    ): CharacterRepository

}