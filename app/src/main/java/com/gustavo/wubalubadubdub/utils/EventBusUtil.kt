package com.gustavo.wubalubadubdub.utils

import org.greenrobot.eventbus.EventBus

object EventBusUtil {

    fun dispatcherEvent(event: BaseEvent) {
        EventBus.getDefault().post(event)
    }

    fun register(subscriber: Any) {
        EventBus.getDefault().register(subscriber)
    }

    fun unregister(subscriber: Any) {
        EventBus.getDefault().unregister(subscriber)
    }
}