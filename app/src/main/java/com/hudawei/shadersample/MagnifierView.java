package com.hudawei.shadersample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hudawei on 2017/5/11.
 */

public class MagnifierView extends View {

    private static final int FACTOR = 2;
    private static final int RADIUS = 100;
    private Bitmap mBitmap;
    private Bitmap mScaleBitmap;
    private BitmapShader mBitmapShader;
    private ShapeDrawable mShapeDrawable;
    private Matrix mMatrix;

    public MagnifierView(Context context) {
        super(context);
    }

    public MagnifierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.hsq);

        mScaleBitmap = Bitmap.createScaledBitmap(mBitmap, mBitmap.getWidth() * FACTOR,
                mBitmap.getHeight() * FACTOR, false);

        mBitmapShader = new BitmapShader(mScaleBitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);

        mShapeDrawable = new ShapeDrawable(new OvalShape());
        mShapeDrawable.getPaint().setShader(mBitmapShader);
        mShapeDrawable.setBounds(0, 0, 2 * RADIUS, 2 * RADIUS);

        mMatrix = new Matrix();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, null);

        mShapeDrawable.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        mMatrix.setTranslate(RADIUS - x * FACTOR, RADIUS - y * FACTOR);
        mBitmapShader.setLocalMatrix(mMatrix);

        mShapeDrawable.setBounds(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS);

        invalidate();
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mBitmap.getWidth(), mBitmap.getHeight());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e("onDetachedFromWindow","onDetachedFromWindow");
        mBitmap.recycle();
        mScaleBitmap.recycle();
    }
}
