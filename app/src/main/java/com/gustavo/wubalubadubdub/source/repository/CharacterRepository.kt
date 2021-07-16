package com.gustavo.wubalubadubdub.source.repository

import androidx.paging.PagingData
import com.gustavo.wubalubadubdub.model.Characters
import io.reactivex.Flowable

interface CharacterRepository {
    fun getCharacters(): Flowable<PagingData<Characters>>
}