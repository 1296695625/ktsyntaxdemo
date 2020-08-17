package com.example.administrator.epgiskotlin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by liu on 2020/7/29.
 */

public class CustomBitmapView extends View {

    private Paint pathPaint;
    private Paint drawablePaint;
    private Path mPath;
    private int bitmapWidth = 200, bitmapHeight = 100;//默认为200，300,width,height必须>=0

    private Bitmap bitmap, temBitmap;

    private float x, y;

    public CustomBitmapView(Context context) {
        super(context);
        init();
    }

    public CustomBitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //
    public void setBitmap(Bitmap bitmap) {
        this.temBitmap = bitmap;
        if (null != temBitmap) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            temBitmap.isMutable();
            Log.v("liu", "test bitmap is mutable");
//            temBitmap.setWidth(200);
//            temBitmap.setHeight(300);
        }
    }

    public CustomBitmapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_POINTER_DOWN) {
            Log.v("liu", "dispatch event--" + event.getAction());
//            return true;
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * 获取x，y，根据end和start坐标点x轴上，y轴上之差（math.abs(endx-startx),math.abs(endy-starty)）来区别是x轴或者是y轴
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_POINTER_INDEX_SHIFT:
                Log.v("liu", "event --shift" + event.getAction());
                break;
            case MotionEvent.ACTION_POINTER_INDEX_MASK:
                Log.v("liu", "event --mask" + event.getActionMasked());
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.v("liu", "event --down" + event.getActionMasked());
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.v("liu", "event --up" + event.getAction());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.quadTo(x, y, event.getX(), event.getY());
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                invalidate();//刷新
                break;
        }
        return true;
    }

    /**
     * 重置
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
//            setMeasuredDimension(bitmapWidth, bitmapHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (null != temBitmap) {
            canvas.drawBitmap(temBitmap, 0, 0, new Paint());
        } else {
            canvas.drawBitmap(bitmap, 0, 0, drawablePaint);
        }
        canvas.drawPath(mPath, pathPaint);
    }
}
