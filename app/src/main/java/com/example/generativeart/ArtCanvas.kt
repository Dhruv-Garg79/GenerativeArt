package com.example.generativeart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import kotlin.math.*

class ArtCanvas(context: Context) : View(context) {

    var w = 0
    var h = 0

    private val paint = Paint().apply {
        strokeWidth = 8f
        isAntiAlias = true // smooths out edges of what is drawn without affecting shape
        isDither =
            true // Dithering affects how colors with higher-precision than the device are down-sampled.
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let { renderPainting(it, 6, 6) }
    }

    private fun renderPainting(canvas: Canvas, iter: Int, initIter: Int) {
        if (iter == 0)
            return

        var distance = 0.0
        val c = (Math.random() * 16777215).toInt() or (0xFF shl 24)
        paint.apply {
            color = c
        }
        for (i in 0..1000) {
            canvas.drawCircle(
                (w/2 +(distance * atan(distance) * sin(distance) * cos(distance))).toFloat(),
                ((distance * tan(distance) * sin(distance) * cos(distance))).toFloat(),
                6f - (0.5f * (initIter - iter)),
                paint
            )

            distance += 0.2 + (0.2 * (initIter - iter));
        }

        renderPainting(canvas, iter-1, initIter)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.w = w
        this.h = h
    }
}