package com.example.administrator.epgiskotlin.view

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View

/**
 * Created by liu on 2020/7/28.
 * this class is writen by kotlin language, to cunstruct a bitmap with your custom symbol
 *
 * CustomView java to kotlin
 */
class CustomViewKt(context: Context?) : View(context) {
    private lateinit var pathPaint: Paint
    private lateinit var drawablePaint: Paint
    private lateinit var mCanvas: Canvas;
    private lateinit var mPath: Path
    private lateinit var bitmap: Bitmap
    private var pathwidth: Int = 0
    private var startX: Float = 0f
    private var startY: Float = 0f

    open fun getBitmap(): Bitmap {
        return bitmap
    }

    init {
        pathPaint = Paint()
        pathPaint.setColor(Color.RED)
        pathPaint.strokeWidth = 1f//默认是1
        drawablePaint = Paint()
        mPath = Path()
        bitmap = Bitmap.createBitmap(200, 300, Bitmap.Config.ARGB_8888)
    }

    open fun setPathWidth(pathWidth: Float) {
        pathPaint!!.strokeWidth = pathWidth//不能为空
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        startX = event.x
        startY = event.y
        if (event.action == MotionEvent.ACTION_DOWN) {
            mPath.rMoveTo(startX, startY)
        } else if (event.action == MotionEvent.ACTION_MOVE) {
            mPath.rQuadTo(startX, startY, event.x, event.y)
            startX = event.x
            startY = event.y
        } else if (event.action == MotionEvent.ACTION_UP) {
            invalidate()
        }
        return true
    }

    open fun reset() {
        mPath!!.reset()
    }

    override fun onDraw(canvas: Canvas?) {
//        this.mCanvas = canvas!!;
        canvas?.drawBitmap(bitmap, 0f, 0f, drawablePaint)
        canvas?.drawPaint(pathPaint)
    }
}