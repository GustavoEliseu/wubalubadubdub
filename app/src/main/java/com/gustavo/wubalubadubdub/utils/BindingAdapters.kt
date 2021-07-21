package com.gustavo.wubalubadubdub.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.gustavo.wubalubadubdub.utils.extensions.getParentActivity
import com.gustavo.wubalubadubdub.utils.extensions.load
import com.gustavo.wubalubadubdub.utils.extensions.loadBorderRound

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

@BindingAdapter("imageUrlBorderRound")
fun setImageUrlRound(view: ImageView,imageUrl: String?){
    view.loadBorderRound(imageUrl)
}

@BindingAdapter("roundBackgroundColor")
fun setRoundBackgroundColor(view: AppCompatImageView, id:Int){
    safeRun {
    view.setBackgroundColor(ContextCompat.getColor(view.context,id))
    }
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

@BindingAdapter("mutableTitle")
fun setMutableTitle(view:Toolbar, text:MutableLiveData<String>?){
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value ->
            view.title = value ?: ""
        })
    }
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(
            parentActivity,
            Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}