package com.example.generativeart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import kotlinx.coroutines.*
import kotlin.math.*

class ArtCanvas(context: Context) : View(context) {

    var w = 0
    var h = 0
    var iter = 6
    var initIter = 6

    private val paint = Paint().apply {
        strokeWidth = 8f
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let { renderAnim(it) }
    }

    private fun renderAnim(canvas: Canvas) = runBlocking {
        val c = (Math.random() * 16777215).toInt() or (0xFF shl 24)
        paint.apply {
            color = c
        }

        CoroutineScope(Dispatchers.Default).launch {
            renderPainting(canvas)
        }
    }

    private suspend fun renderPainting(canvas: Canvas){
        var distance = 0.0
        for (i in 0..1000) {
            canvas.drawCircle(
                (w / 2 + (distance * atan(distance) * sin(distance) * cos(distance/2))).toFloat(),
                (h / 2 + (distance * tan(distance) * sin(distance/2) * cos(distance))).toFloat(),
                6f - (0.5f * (initIter - iter)),
                paint
            )

            distance += 0.2 + (0.2 * (initIter - iter));
        }

        delay(200)

        if (iter > 0) {
            iter--
            invalidate()
        }
        else{
            iter = initIter
            invalidate()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.w = w
        this.h = h
    }
}
