package com.gustavo.wubalubadubdub

import com.gustavo.wubalubadubdub.model.Character
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PagingCharacters {

    @GET("character")
    fun getRickMortyCharacters(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("name") name:String?,
        @Query("status") status: String?
    ): Single<Character>
    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/character/"

        fun create(): PagingCharacters {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(PagingCharacters::class.java)
        }
    }
}