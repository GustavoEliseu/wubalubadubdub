package com.gustavo.wubalubadubdub.utils

import android.widget.Toast

class RxEvent {

    data class EventMessageRef(val messageId: Int) :BaseEvent()


    data class EventMessage(val message: String, val duration: Int = Toast.LENGTH_SHORT) :BaseEvent()
}