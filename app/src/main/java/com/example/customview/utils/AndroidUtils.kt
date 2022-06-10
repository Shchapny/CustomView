package com.example.customview.utils

import android.content.Context
import kotlin.math.ceil

object AndroidUtils {

    fun dp(context: Context, dp: Float): Int {
        return if (dp == 0f) 0 else ceil(
            context.resources.displayMetrics.density * dp
        ).toInt()
    }
}