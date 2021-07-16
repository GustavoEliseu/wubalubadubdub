package com.gustavo.wubalubadubdub.ui.paging

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.gustavo.wubalubadubdub.PagingCharacters
import com.gustavo.wubalubadubdub.model.Characters
import com.gustavo.wubalubadubdub.model.CharacterList
import com.gustavo.wubalubadubdub.model.CharacterMapper
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetCharactersPagingSource(
        private val service: PagingCharacters,
        private val apiKey: String,
        private val mapper: CharacterMapper,
        private val status:String?= null,
        private val name: String?= null
    ) : RxPagingSource<Int, Characters>() {

        override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Characters>> {
            val position = params.key ?: 1

            return service.getRickMortyCharacters(apiKey, position, name = name, status = status)
                .subscribeOn(Schedulers.io())
                .map{mapper.transform(it, position)}
                .map { toLoadResult(it, position) }
                .onErrorReturn { LoadResult.Error(it) }
        }

        private fun toLoadResult(data: CharacterList, position: Int): LoadResult<Int, Characters> {
            return LoadResult.Page(
                data = data.charactersList,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == data.count) null else position + 1
            )
        }

    override fun getRefreshKey(state: PagingState<Int, Characters>): Int? {
        TODO("Not yet implemented")
    }
}