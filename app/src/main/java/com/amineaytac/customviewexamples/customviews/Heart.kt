package com.amineaytac.customviewexamples.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class Heart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val viewRectF = RectF()
    private val framePath = Path()

    private val sweepAngle = 225f

    private val heartPaint = Paint().apply {
        color = Color.RED
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewRectF.set(0f, 0f, w.toFloat(), h.toFloat())
        initFramePath()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(framePath, heartPaint)
    }

    private fun initFramePath() {

        val centerX = viewRectF.width() / 2
        val centerY = viewRectF.height() / 2

        val heartWidth = viewRectF.width()
        val heartHeight = viewRectF.height()
        val bottomPointY = centerY + heartHeight / 2

        framePath.apply {
            reset()

            addArc(
                centerX - heartWidth / 2,
                centerY - heartHeight / 2,
                centerX,
                centerY,
                -225f,
                sweepAngle
            )

            arcTo(
                centerX,
                centerY - heartHeight / 2,
                centerX + heartWidth / 2,
                centerY,
                -180f,
                sweepAngle,
                false
            )

            lineTo(centerX, bottomPointY)
            close()
        }
        invalidate()
    }
}