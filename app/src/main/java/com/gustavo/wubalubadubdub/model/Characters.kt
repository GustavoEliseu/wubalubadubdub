package com.gustavo.wubalubadubdub.model

import com.google.gson.annotations.SerializedName
import com.gustavo.wubalubadubdub.utils.extensions.isNullOrEmptyOrBlank
import java.io.Serializable
import java.util.*

data class CharacterResponse<T>(
    @SerializedName("info") val info:RequestInfo,
    val results: List<T> = listOf()
)

data class RequestInfo(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)

//data class CharacterList(
//    val count:Int,
//    val page:Int,
//    val lastPage: Int,
//    val charactersList:List<Characters>
//)

data class Characters(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: SimpleLocation,
    val location: SimpleLocation,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: Date,
    var firstEpisode: String? = null,
    var lastEpisode: String? = null
):Serializable {
    constructor() : this(0,"","","","","",SimpleLocation(),SimpleLocation(),"", listOf(),"",Date())

    fun isEmpty(): Boolean{
        return(name.isNullOrEmptyOrBlank() && status.isNullOrEmptyOrBlank() &&  image.isNullOrEmptyOrBlank() && species.isNullOrEmptyOrBlank())
    }

}