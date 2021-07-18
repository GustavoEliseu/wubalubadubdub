package com.gustavo.wubalubadubdub.model

import java.io.Serializable

data class SimpleLocation(val name : String,val url: String):Serializable {
    constructor(): this ("","")
}