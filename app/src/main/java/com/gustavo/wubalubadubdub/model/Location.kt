package com.gustavo.wubalubadubdub.model

import java.io.Serializable
import java.util.*

data class SimpleLocation(val name: String, val url: String) : Serializable {
    constructor() : this("", "")
}

data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: Date
)