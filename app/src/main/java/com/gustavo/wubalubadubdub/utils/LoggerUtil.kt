package com.gustavo.wubalubadubdub.utils

import com.gustavo.wubalubadubdub.BuildConfig

object LoggerUtil {

    fun printStackTraceOnlyInDebug(throwable: Throwable){
        //If needed add Firebase crashalytics
        if(BuildConfig.DEBUG){
            throwable.printStackTrace()
        }
    }

    fun printlnOnlyInDebug(message: String){
        if(BuildConfig.DEBUG){
            println(message)
        }
    }
}