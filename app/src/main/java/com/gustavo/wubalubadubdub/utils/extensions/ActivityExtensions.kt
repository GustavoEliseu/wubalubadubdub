package com.gustavo.wubalubadubdub.utils.extensions

import android.app.Activity
import android.widget.Toast


fun Activity.toast(id:Int, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,id,length).show()
}

fun Activity.toast(message:String, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,message,length).show()
}