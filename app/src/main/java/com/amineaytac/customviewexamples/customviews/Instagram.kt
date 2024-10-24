package com.amineaytac.customviewexamples.customviews

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.amineaytac.customviewexamples.R

class Instagram @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    private val viewRectF = RectF()
    private var framePath = Path()

    private val circleOnePaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    private val circleTwoPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = 18f
        color = ContextCompat.getColor(context, R.color.white)
    }

    private val framePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.white)
        style = Paint.Style.STROKE
        strokeWidth = 18.toFloat()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewRectF.set(0f, 0f, w.toFloat(), h.toFloat())
        initFramePath()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        circleOnePaint.shader = LinearGradient(
            0f, 0f, 0f,
            height.toFloat(),
            intArrayOf(
                Color.parseColor("#833AB4"), // Purple
                Color.parseColor("#DD2A7B"), // Pink
                Color.parseColor("#FD1D1D"), // Red
                Color.parseColor("#FCA135"), // Orange
                Color.parseColor("#F8C501")  // Yellow
            ),
            null,
            Shader.TileMode.CLAMP
        )

        circleOne(canvas)
        canvas.drawPath(framePath, framePaint)
        circleTwo(canvas)
    }

    private fun circleOne(canvas: Canvas) {
        val radius = (viewRectF.width().coerceAtMost(viewRectF.height()) / 2)
        canvas.drawCircle(
            (viewRectF.width() / 2), (viewRectF.height() / 2),
            radius, circleOnePaint
        )
        invalidate()
    }

    private fun circleTwo(canvas: Canvas) {
        val radius = (viewRectF.width().coerceAtMost(viewRectF.height()) / 8)
        canvas.drawCircle(
            (viewRectF.width() / 2), (viewRectF.height() / 2),
            radius, circleTwoPaint
        )
        invalidate()
    }

    private fun initFramePath() {
        val cornerRadius = 100f
        framePath.reset()

        val squareSize = minOf(viewRectF.width(), viewRectF.height()) * 0.6f
        val left = (viewRectF.width() - squareSize) / 2
        val top = (viewRectF.height() - squareSize) / 2
        val right = left + squareSize
        val bottom = top + squareSize

        framePath.moveTo(left + cornerRadius, top)
        framePath.lineTo(right - cornerRadius, top)
        framePath.arcTo(
            right - 2 * cornerRadius, top,
            right, top + 2 * cornerRadius,
            270f, 90f, false
        )

        framePath.lineTo(right, bottom - cornerRadius)
        framePath.arcTo(
            right - 2 * cornerRadius, bottom - 2 * cornerRadius,
            right, bottom,
            0f, 90f, false
        )

        framePath.lineTo(left + cornerRadius, bottom)
        framePath.arcTo(
            left, bottom - 2 * cornerRadius,
            left + 2 * cornerRadius, bottom,
            90f, 90f, false
        )

        framePath.lineTo(left, top + cornerRadius)
        framePath.arcTo(
            left, top,
            left + 2 * cornerRadius, top + 2 * cornerRadius,
            180f, 90f, false
        )

        framePath.close()
        invalidate()
    }
}