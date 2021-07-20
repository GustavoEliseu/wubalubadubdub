package com.gustavo.wubalubadubdub.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.gustavo.wubalubadubdub.utils.extensions.getParentActivity
import com.gustavo.wubalubadubdub.utils.extensions.load

@BindingAdapter("mutableImageUrl")
fun setMutableImageUrl(view: ImageView, imageUrl: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity !=null && imageUrl != null) {
        imageUrl.observe(parentActivity, Observer { value ->
            view.load(value)
        })
    }
}

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, imageUrl: String?){
    view.load(imageUrl)
}


@BindingAdapter("mutableText")
fun setMutableText(view:TextView, text:MutableLiveData<String>?){

    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value ->
            view.text = value ?: ""
        })
    }
}