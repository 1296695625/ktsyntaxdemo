package com.example.administrator.epgiskotlin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by liu on 2020/7/28.
 */

public class CustomView extends View {

    private Paint pathPaint;
    private Paint drawablePaint;
    private Path mPath;
    private int bitmapWidth=1, bitmapHeight=1;//默认为200，300,width,height必须>=0

    private Bitmap bitmap;

    private float x, y;

    private Canvas mCanvas;//未使用

    public CustomView(Context context) {
        super(context);
        init();
    }

    public void setBitmapWidth(int bitmapWidth) {
        this.bitmapWidth = bitmapWidth;
    }

    public void setBitmapHeight(int bitmapHeight) {
        this.bitmapHeight = bitmapHeight;
    }

    /**
     * 设置画笔的宽度
     *
     * @param width
     */
    public void setPathWidth(float width) {
        if (null != pathPaint) {
            pathPaint.setStrokeWidth(width);
        }
    }

    private void init() {
        pathPaint = new Paint();
        pathPaint.setAntiAlias(true);
        pathPaint.setColor(Color.RED);
        pathPaint.setStyle(Paint.Style.STROKE);
        drawablePaint = new Paint();
        drawablePaint.setColor(Color.WHITE);
        mPath = new Path();
        bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mPath.quadTo(x, y, event.getX(), event.getY());
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
//                if (null != mCanvas) mCanvas.drawPath(mPath, pathPaint);
                invalidate();//刷新绘制
                break;
        }
        return true;
    }

    /**
     * 重绘
     */
    public void reset() {
        if (null != mPath) {
            mPath.reset();
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (0 != bitmapHeight && 0 != bitmapHeight) {
            setMeasuredDimension(bitmapWidth, bitmapHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.mCanvas = canvas;
        canvas.drawBitmap(bitmap, 0, 0, drawablePaint);
        canvas.drawPath(mPath, pathPaint);
    }
}
