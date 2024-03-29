package com.gustavo.wubalubadubdub.source.remote

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gustavo.wubalubadubdub.BuildConfig
import com.gustavo.wubalubadubdub.model.Characters
import timber.log.Timber

class CharactersPagingDataSource(private val service: Api) :
    PagingSource<Int, Characters>() {

    private var characterSearch: String? = null

    fun updateSearch(search:String?){
        characterSearch = search
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Characters> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getCharacterList(pageNumber, characterSearch)
            val pagedResponse = response.body()
            if(BuildConfig.DEBUG)
                Timber.tag("requestBody:").e(response.raw().toString())
            val data = pagedResponse?.results

            var nextPageNumber: Int? = null
            if (pagedResponse?.info?.next != null) {
                val uri = Uri.parse(pagedResponse.info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Characters>): Int = 1
}