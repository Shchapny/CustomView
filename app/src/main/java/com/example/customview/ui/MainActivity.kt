package com.example.customview.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import com.example.customview.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = findViewById<StatsView>(R.id.stats)
        view.postDelayed({
            view.data = listOf(
                500f,
                500f,
                500f,
                500f
            )

//цепочка анимаций
//            val alpha = ObjectAnimator.ofFloat(view, View.ALPHA, 0.25f, 1f).apply {
//                duration = 3000
//                interpolator = LinearInterpolator()
//            }
//            val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0f, 1f)
//            val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 1f)
//            val scale = ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY).apply {
//                duration = 3000
//                interpolator = BounceInterpolator()
//            }
//            AnimatorSet().apply {
//                playSequentially(scale, alpha)
//            }.start()

//            view.animate()
//                .rotation(360f)
//                .scaleX(1.2f)
//                .scaleY(1.2f)
//                .setInterpolator(LinearInterpolator())
//                .setStartDelay(500)
//                .setDuration(500)
//                .start()

//внешняя анимация, объединяет поворот и прозрачность
//            val rotation = PropertyValuesHolder.ofFloat(View.ROTATION, 0f, 360f)
//            val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f)
//            ObjectAnimator.ofPropertyValuesHolder(view, rotation, alpha).apply {
//                duration = 5000
//                interpolator = LinearInterpolator()
//            }.start()

// прозрачность
// внешняя анимация
//            ObjectAnimator.ofFloat(view, View.ALPHA, 0.25f, 1f).apply {
//                duration = 3000
//                interpolator = LinearInterpolator()
//            }.start()

// Анимация с помощью xml
//            view.startAnimation(
//                AnimationUtils.loadAnimation(this, R.anim.view_animation)
//            )
        }, 3000)
    }
}