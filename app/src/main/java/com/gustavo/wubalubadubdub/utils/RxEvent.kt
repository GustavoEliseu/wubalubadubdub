package com.gustavo.wubalubadubdub.utils

import android.widget.Toast

class RxEvent {

    data class EventMessageRef(val message: Int)


    data class EventMessage(val message: String, val duration: Int = Toast.LENGTH_SHORT)
}