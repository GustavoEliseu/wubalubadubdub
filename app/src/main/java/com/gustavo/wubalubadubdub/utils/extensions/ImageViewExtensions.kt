package com.gustavo.wubalubadubdub.utils.extensions

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.gustavo.wubalubadubdub.R

fun ImageView.load(url: String?) {
        if (url != null) {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(circularProgressDrawable)
                .apply(myRequestOptionGlide())
                .into(this)
        }
    }

fun ImageView.loadBorderRound(url: String?) {
    if (url != null) {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()



        Glide.with(context)
            .load(url)
            .transform(RoundedCorners(10.toPx()))
            .placeholder(circularProgressDrawable)
            .apply(myRequestOptionGlide())
            .into(this)
    }
}

private fun myRequestOptionGlide() = RequestOptions().apply {
    skipMemoryCache(false)
    diskCacheStrategy(DiskCacheStrategy.ALL)
}