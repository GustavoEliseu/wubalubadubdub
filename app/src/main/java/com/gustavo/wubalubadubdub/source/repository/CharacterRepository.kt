package com.gustavo.wubalubadubdub.source.repository

import com.gustavo.wubalubadubdub.model.CharacterResponse
import com.gustavo.wubalubadubdub.source.remote.Api
import io.reactivex.Observable
import retrofit2.http.Query
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val api: Api
) {

    fun getCharacterList( apiKey: String, page: Int, name:String?= null,status: String? = null) : Observable<List<CharacterResponse>>{
        return api.getCharacterList(apiKey,page,name,status)
    }
}