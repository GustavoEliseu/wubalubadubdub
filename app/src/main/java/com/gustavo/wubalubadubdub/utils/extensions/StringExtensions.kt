package com.gustavo.wubalubadubdub.utils.extensions

fun String?.isNullOrEmptyOrBlank(): Boolean {
    return isNullOrEmpty() || isNullOrBlank()
}

fun String?.isNotNullOrNotEmptyOrNotBlank(): Boolean {
    return this.isNullOrEmptyOrBlank().not()
}