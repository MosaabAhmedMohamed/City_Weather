package com.example.cityweather.presentation.ui.util

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.DecelerateInterpolator
import android.view.animation.ScaleAnimation

object Animation {

    fun getScaleAnimation(offset: Int, duration: Int): android.view.animation.Animation {
        val anim = ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f)
        anim.fillAfter = true
        anim.startOffset = offset.toLong()
        anim.duration = duration.toLong()
        return anim
    }

    fun getFadeInAnimation(offset: Int, duration: Int): android.view.animation.Animation {
        val fadeIn = AlphaAnimation(0.0f, 1.0f)
        fadeIn.startOffset = offset.toLong()
        fadeIn.interpolator = DecelerateInterpolator()
        fadeIn.duration = duration.toLong()
        return fadeIn
    }

    fun setBouncingAnimation(view: View, offset: Int, duration: Int): ObjectAnimator {
        val objAnim = ObjectAnimator.ofPropertyValuesHolder(
            view,
            PropertyValuesHolder.ofFloat("scaleX", 1.05f),
            PropertyValuesHolder.ofFloat("scaleY", 1.05f)
        )
        objAnim.duration = duration.toLong()
        objAnim.startDelay = offset.toLong()
        objAnim.repeatCount = 40
        objAnim.repeatMode = ValueAnimator.REVERSE
        objAnim.start()
        return objAnim
    }

    fun setAnimationToView(
        view: View,
        animation: android.view.animation.Animation
    ): View {
        view.startAnimation(animation)
        return view
    }

    fun expand(v: View, duration: Int, targetHeight: Int) {
        val prevHeight = v.height
        v.visibility = View.VISIBLE
        val valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight)
        valueAnimator.addUpdateListener { animation: ValueAnimator ->
            v.layoutParams.height = animation.animatedValue as Int
            v.requestLayout()
        }
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.duration = duration.toLong()
        valueAnimator.start()
        valueAnimator.addUpdateListener { animation: ValueAnimator ->
            val animProgress = animation.animatedValue as Int
            if (animProgress == 100) {
                v.visibility = View.VISIBLE
            }
        }
    }

    fun collapse(v: View, duration: Int, targetHeight: Int) {
        val prevHeight = v.height
        val valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight)
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.addUpdateListener { animation: ValueAnimator ->
            v.layoutParams.height = animation.animatedValue as Int
            v.requestLayout()
        }
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.duration = duration.toLong()
        valueAnimator.start()
        valueAnimator.addUpdateListener { animation: ValueAnimator ->
            val animProgress = animation.animatedValue as Int
            if (animProgress == 100) {
                v.visibility = View.GONE
            }
        }
    }

    fun FlipX(view: View?, duration: Int) {
        val flip = ObjectAnimator.ofFloat(view, "rotationY", 360f, 0f)
        flip.duration = duration.toLong()
        flip.start()
    }

}