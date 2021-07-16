package com.gustavo.wubalubadubdub.source.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.gustavo.wubalubadubdub.model.Characters
import com.gustavo.wubalubadubdub.ui.paging.GetCharactersPagingSource
import io.reactivex.Flowable

class CharacterRepositoryImpl(val pagingSource: GetCharactersPagingSource): CharacterRepository {

    override fun getCharacters(): Flowable<PagingData<Characters>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 40),
            pagingSourceFactory = { pagingSource }
        ).flowable
    }
}