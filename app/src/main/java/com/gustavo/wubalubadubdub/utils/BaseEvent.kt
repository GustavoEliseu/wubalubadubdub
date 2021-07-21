package com.gustavo.wubalubadubdub.utils

abstract class BaseEvent {
    fun dispatcherEvent(){
        EventBusUtil.dispatcherEvent(this)
    }
}