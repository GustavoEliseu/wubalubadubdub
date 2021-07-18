package com.gustavo.wubalubadubdub.utils

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object RxBus {

    private val publisher = PublishSubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    fun publishOnMainThread(event: Any) {
        safeRunOnUiThread{
            publish(event)
        }
    }

    // Listen should return an Observable and not the publisher
    // Using ofType we filter only events that match that class recordType
    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)
}