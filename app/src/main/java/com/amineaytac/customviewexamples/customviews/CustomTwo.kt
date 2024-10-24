package com.amineaytac.customviewexamples.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.amineaytac.customviewexamples.R

class CustomTwo @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        setWillNotDraw(false)
    }

    private val viewRectF = RectF()

    private val framePath = Path()
    private val framePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.black)
        style = Paint.Style.STROKE
        strokeWidth = 18.toFloat()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewRectF.set(0f, 0f, w.toFloat(), h.toFloat())
        initFramePath()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.clipPath(framePath)
        canvas.drawPath(framePath, framePaint)
    }

    private fun initFramePath() {
        val cornerRadius = 100f
        framePath.reset()

        framePath.moveTo(viewRectF.left, viewRectF.top)
        framePath.lineTo(viewRectF.right, viewRectF.top)
        framePath.lineTo(viewRectF.right, viewRectF.bottom)
        framePath.lineTo(viewRectF.left + cornerRadius, viewRectF.bottom)
        framePath.arcTo(
            viewRectF.left,
            viewRectF.bottom - 2 * cornerRadius,
            2 * cornerRadius,
            viewRectF.bottom,
            90f,
            90f,
            false
        )
        framePath.lineTo(viewRectF.left, viewRectF.top + cornerRadius)
        framePath.close()

        invalidate()
    }
}