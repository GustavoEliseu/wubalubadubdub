package com.gustavo.wubalubadubdub.di.module

import com.gustavo.wubalubadubdub.source.repository.CharacterRepository
import com.gustavo.wubalubadubdub.source.repository.CharacterRepositoryImpl
import com.gustavo.wubalubadubdub.source.repository.EpisodeRepository
import com.gustavo.wubalubadubdub.source.repository.EpisodeRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl
    ): CharacterRepository


    @Binds
    abstract fun bindEpisodeRepository(
        episodeRepositoryImpl: EpisodeRepositoryImpl
    ): EpisodeRepository

}