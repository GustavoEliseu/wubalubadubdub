package com.gustavo.wubalubadubdub.source.repository

import androidx.paging.PagingData
import com.gustavo.wubalubadubdub.model.Characters
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(): Flow<PagingData<Characters>>
}