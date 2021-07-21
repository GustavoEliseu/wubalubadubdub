package com.gustavo.wubalubadubdub.utils.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

fun Activity.showKeyboard(editText: EditText?) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    editText?.postDelayed({
        editText.requestFocus()
        imm.showSoftInput(editText, 0)
    }, 100)
    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
}

fun <T : Any> MutableLiveData<T>.obs(lifecycleOwner: LifecycleOwner, toObserve: (T?) -> Unit) {
    this.observe(lifecycleOwner, Observer { errorMessage ->
        toObserve(errorMessage)
    })
}