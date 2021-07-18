package com.gustavo.wubalubadubdub.utils

import android.os.Handler
import android.os.Looper

fun safeRun(run: () -> Unit) {
    try {
        run()
    }catch (e: Exception){
        LoggerUtil.printStackTraceOnlyInDebug(e)
    }
}

fun runOnUiThread(r: () -> Unit) {
    Handler(Looper.getMainLooper()).post(r)
}

fun safeRunOnUiThread(r: () -> Unit) {
    runOnUiThread(r = {
        safeRun(r)
    })
}


fun unhandledTry(execute: () -> Unit) {
    try {
        execute()
    } catch (e: Exception) {
    }
}