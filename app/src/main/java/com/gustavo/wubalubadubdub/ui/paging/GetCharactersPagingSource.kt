package com.gustavo.wubalubadubdub.ui.paging

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.gustavo.wubalubadubdub.model.Character
import com.gustavo.wubalubadubdub.model.CharacterResponse
import com.gustavo.wubalubadubdub.source.repository.CharacterRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetCharactersPagingSource(
        private val service: CharacterRepository,
        private val apiKey: String,
        private val status:String?= null,
        private val name: String?= null
    ) : RxPagingSource<Int, Character>() {

        override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Character>> {
            val position = params.key ?: 1

            return service.getCharacterList(apiKey, position, name = name, status = status)
                .subscribeOn(Schedulers.io())
                .map { mapper.transform(it) }
                .map { toLoadResult(it, position) }
                .onErrorReturn { LoadResult.Error(it) }
        }

        private fun toLoadResult(data: CharacterResponse, position: Int): LoadResult<Int, Character> {
            return LoadResult.Page(
                data = data.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == data.info.pages) null else position + 1
            )
        }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        TODO("Not yet implemented")
    }
}
}