package com.gustavo.wubalubadubdub.utils.extensions

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

private const val COMPOUND_DRAWABLE_RIGHT_INDEX = 2

fun EditText.makeClearableEditText(onIsNotEmpty: (() -> Unit)?, onCanceled: (() -> Unit)?) {
    compoundDrawables[COMPOUND_DRAWABLE_RIGHT_INDEX]?.let { clearDrawable ->
        makeClearableEditText(onIsNotEmpty, onCanceled, clearDrawable)
    }
}

fun EditText.makeClearableEditText(
    onIsNotEmpty: (() -> Unit)?,
    onCanceled: (() -> Unit)?,
    clearDrawable: Drawable
) {
    val updateRightDrawable = {
        this.setCompoundDrawables(
            null,
            null,
            if (text.isNotEmpty()) clearDrawable else null,
            null
        )

    }
    updateRightDrawable()

    this.afterTextChanged {
        if (it.isNotEmpty()) {
            onIsNotEmpty?.invoke()
        }
        updateRightDrawable()
    }
    this.onRightDrawableClicked {
        this.text.clear()
        this.setCompoundDrawables(null, null, null, null)
        onCanceled?.invoke()
        this.requestFocus()
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

@SuppressLint("ClickableViewAccessibility")
fun EditText.onRightDrawableClicked(onClicked: (view: EditText) -> Unit) {
    this.setOnTouchListener { v, event ->
        var hasConsumed = false
        if (v is EditText) {
            if (event.x >= v.width - v.totalPaddingRight) {
                if (event.action == MotionEvent.ACTION_UP) {
                    onClicked(this)
                }
                hasConsumed = true
            }
        }
        hasConsumed
    }
}

@SuppressLint("CheckResult")
fun EditText.searchWithDelay(delay: Long, action: (String) -> Unit) {
    Observable.create(ObservableOnSubscribe<String> { subscriber ->
        this.addTextChangedListener {
            subscriber.onNext(it.toString())
        }
    }).subscribeOn(Schedulers.io())
        .map { text -> text.lowercase(Locale.getDefault()).trim() }
        .debounce(delay, TimeUnit.MILLISECONDS)
        .subscribe { text ->
            action(text)
        }
}