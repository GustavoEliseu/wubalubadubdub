package com.gustavo.wubalubadubdub.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.load(url: String?) {
        if (url != null) {
            Glide
                .with(context)
                .load(url)
                .centerCrop()
                .apply(myRequestOptionGlide())
                .into(this)
        }
    }

private fun myRequestOptionGlide() = RequestOptions().apply {
    skipMemoryCache(false)
    diskCacheStrategy(DiskCacheStrategy.ALL)
}