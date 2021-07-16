package com.gustavo.wubalubadubdub.source.repository

import com.gustavo.wubalubadubdub.model.CharacterResponse
import com.gustavo.wubalubadubdub.source.remote.Api
import io.reactivex.Observable
import retrofit2.http.Query
import javax.inject.Inject

class aaa @Inject constructor(
    private val api: Api
) {

    fun getCharacterList( apiKey: String, page: Int, name:String?= null,status: String? = null) : Observable<CharacterResponse>{
        return api.getCharacterList(apiKey,page,name,status)
    }
}