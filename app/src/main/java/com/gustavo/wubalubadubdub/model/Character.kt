package com.gustavo.wubalubadubdub.model

import dagger.multibindings.IntoMap
import java.util.*

data class CharacterResponse(
    val info:RequestInfo,
    val results: List<Character>
)

data class RequestInfo(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: SimpleLocation,
    val location: SimpleLocation,
    val image: String,
    val episode: SimpleEpisode,
    val url: String,
    val created: Date
)