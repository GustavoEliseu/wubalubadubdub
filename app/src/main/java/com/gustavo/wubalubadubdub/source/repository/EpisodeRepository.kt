package com.gustavo.wubalubadubdub.source.repository

import androidx.paging.PagingData
import com.gustavo.wubalubadubdub.model.Episodes
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    fun getEpisodes(): Flow<PagingData<Episodes>>
}