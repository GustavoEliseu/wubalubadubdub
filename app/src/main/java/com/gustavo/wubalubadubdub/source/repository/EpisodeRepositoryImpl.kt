package com.gustavo.wubalubadubdub.source.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gustavo.wubalubadubdub.model.Episodes
import com.gustavo.wubalubadubdub.source.remote.Api
import com.gustavo.wubalubadubdub.source.remote.EpisodePagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val service: Api
) : EpisodeRepository {

    override fun getEpisodes(): Flow<PagingData<Episodes>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { EpisodePagingDataSource(service) }
    ).flow
}
