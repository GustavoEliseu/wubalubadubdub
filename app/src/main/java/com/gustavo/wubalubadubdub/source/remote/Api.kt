package com.gustavo.wubalubadubdub.source.remote

import com.gustavo.wubalubadubdub.model.PagingResponse
import com.gustavo.wubalubadubdub.model.Characters
import com.gustavo.wubalubadubdub.model.Episodes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("character/")
    suspend fun getCharacterList(
        @Query("page") page: Int,
        @Query("name") name: String? = null
    ): Response<PagingResponse<Characters>>

    @GET("episode/")
    suspend fun getEpisodeList(
        @Query("page") page: Int,
        @Query("name") name:String? = null
    ) : Response<PagingResponse<Episodes>>

    @GET("location/")
    suspend fun getLocationList(
        @Query("page") page: Int,
        @Query("name") name:String? = null
    ) : Response<PagingResponse<Episodes>>
}