package com.gustavo.wubalubadubdub.utils.extensions

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.gustavo.wubalubadubdub.R
import io.codetail.animation.ViewAnimationUtils
import kotlin.math.hypot
import kotlin.math.max

fun LinearLayout.showSearchBar(
    appBar: AppBarLayout?,
    container: View?,
    listener: Animator.AnimatorListener
) {
    val toolbar = appBar?.findViewWithTag<Toolbar>("appBarToolbar")
    val tabs = appBar?.findViewWithTag<TabLayout?>("appBarTab")
    val positionFromRight = 2f

    val set = AnimatorSet()
    val tabHeight = tabs?.height?.toFloat() ?: 0f
    set.playTogether(
        ObjectAnimator.ofFloat(appBar, "translationY", -tabHeight),
        ObjectAnimator.ofFloat(container, "translationY", -tabHeight),
        ObjectAnimator.ofFloat(appBar, "alpha", 0f)
    )
    set.setDuration(100).addListener(listener)
    set.start()
    toolbar?.let {
        // start x-index for circular animation
        val cx =
            toolbar.width - (resources.getDimension(R.dimen.dp48) * (0.5f + positionFromRight)).toInt()
        // start y-index for circular animation
        val cy = (toolbar.top + toolbar.bottom) / 2

        // calculate max radius
        val dx = max(cx, toolbar.width - cx)
        val dy = max(cy, toolbar.height - cy)
        val finalRadius = hypot(dx.toDouble(), dy.toDouble()).toFloat()

        // Circular animation declaration begin
        val animator: Animator = ViewAnimationUtils
            .createCircularReveal(this, cx, cy, 0f, finalRadius)
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.duration = 200
        this.visibility = View.VISIBLE
        animator.start()
    }
}

fun LinearLayout.hideSearchBar(
    appBar: AppBarLayout?,
    container: View?,
    listener: Animator.AnimatorListener
) {
    val toolbar = appBar?.findViewWithTag<Toolbar>("appBarToolbar")
    val positionFromRight = 2f
    toolbar?.let {
        // start x-index for circular animation
        val cx =
            toolbar.width - (resources.getDimension(R.dimen.dp48) * (0.5f + positionFromRight)).toInt()
        // start  y-index for circular animation
        val cy = (toolbar.top + toolbar.bottom) / 2

        // calculate max radius
        val dx = max(cx, toolbar.width - cx)
        val dy = max(cy, toolbar.height - cy)
        val finalRadius = hypot(dx.toDouble(), dy.toDouble()).toFloat()

        // Circular animation declaration begin
        val animator: Animator = ViewAnimationUtils
            .createCircularReveal(this, cx, cy, finalRadius, 0f)
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.duration = 200
        animator.addListener(listener)
        animator.start()
        // Circular animation declaration end

        appBar.visibility = View.VISIBLE
        val set = AnimatorSet()
        set.playTogether(
            ObjectAnimator.ofFloat(appBar, "translationY", 0f),
            ObjectAnimator.ofFloat(appBar, "alpha", 1f),
            ObjectAnimator.ofFloat(container, "translationY", 0f)
        )
        set.setDuration(100).start()
    }
}