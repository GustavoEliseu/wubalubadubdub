package com.gustavo.wubalubadubdub.source.remote

import com.gustavo.wubalubadubdub.model.CharacterResponse
import com.gustavo.wubalubadubdub.model.Characters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("character/")
    suspend fun getCharacterList(
        @Query("page") page: Int,
        @Query("id") id:Int? = null,
        @Query("status") status: String? = ""): Response<CharacterResponse<Characters>>

}