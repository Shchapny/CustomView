package com.example.customview.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.example.customview.R
import com.example.customview.utils.AndroidUtils
import kotlin.math.min
import kotlin.random.Random

class StatsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private var radius = 0f
    private var center = PointF(0f, 0f)
    private var oval = RectF(0f, 0f, 0f, 0f)

    private var lineWidth = AndroidUtils.dp(context, 5f).toFloat()
    private var fontSize = AndroidUtils.dp(context, 40f).toFloat()
    private var colors = emptyList<Int>()

    init {
        context.withStyledAttributes(attrs, R.styleable.StatsView) {
            lineWidth = getDimension(R.styleable.StatsView_lineWidth, lineWidth)
            fontSize = getDimension(R.styleable.StatsView_fontSize, fontSize)
            val resId = getResourceId(R.styleable.StatsView_colors, 0)
            colors = resources.getIntArray(resId).toList()
        }
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = lineWidth
        strokeCap = Paint.Cap.ROUND
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = fontSize
    }

    var data = emptyList<Float>()
        set(value) {
            field = value
            invalidate()
        }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = min(w, h) / 2f - lineWidth / 2
        center = PointF(w / 2f, h / 2f)
        oval = RectF(
            center.x - radius, center.y - radius,
            center.x + radius, center.y + radius
        )
    }

    override fun onDraw(canvas: Canvas) {
        if (data.isEmpty()) {
            return
        }

        var startFrom = -90f
        for ((index, datum) in data.withIndex()) {
            val angle = 360f * datum / data.sum()
            paint.color = colors.getOrNull(index) ?: randomColor()
            canvas.drawArc(oval, startFrom, angle, false, paint)
            startFrom += angle
        }

        paint.color = colors.first()
        canvas.drawCircle(center.x, center.y - radius, 1f, paint)

        canvas.drawText(
            "%.2f%%".format(100f),
            center.x,
            center.y + textPaint.textSize / 4,
            textPaint
        )
    }

    private fun randomColor() = Random.nextInt(0xFF000000.toInt(), 0xFFFFFFFF.toInt())
}