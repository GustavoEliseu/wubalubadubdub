package com.gustavo.wubalubadubdub.source.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gustavo.wubalubadubdub.model.Characters
import com.gustavo.wubalubadubdub.source.remote.Api
import com.gustavo.wubalubadubdub.source.remote.CharactersPagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: Api
) : CharacterRepository {
    private val pagingDataSource = CharactersPagingDataSource(service)

    fun updatePagingDatSource(term:String?){
        pagingDataSource.updateSearch(term)
    }

    override fun getCharacters(): Flow<PagingData<Characters>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { pagingDataSource }
    ).flow
}