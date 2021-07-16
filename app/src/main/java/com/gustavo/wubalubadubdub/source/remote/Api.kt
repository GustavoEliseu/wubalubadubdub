package com.gustavo.wubalubadubdub.source.remote

import com.gustavo.wubalubadubdub.model.CharacterResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("character")
    fun getCharacterList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("name") name:String?,
        @Query("status") status: String?): Observable<List<CharacterResponse>>

}