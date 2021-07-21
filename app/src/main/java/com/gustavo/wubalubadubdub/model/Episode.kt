package com.gustavo.wubalubadubdub.model

import java.sql.Date

data class SimpleEpisode(val episodes: List<String>) {
    constructor() : this(listOf())
}

data class Episodes(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: Date
)