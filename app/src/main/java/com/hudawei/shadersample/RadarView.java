package com.hudawei.shadersample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by hudawei on 2017/5/11.
 */

public class RadarView extends View {
    private Paint mBgPaint;
    private Paint mPaint;
    private final static int RADIUS = 300;
    private int[] mColors = {Color.TRANSPARENT, Color.parseColor("#500000FF")};
    private Matrix mMatrix;
    private int mRotate = 30;
    private final static int INTERVAL = 3;
    private SweepGradient mSweepGradient;
    private boolean mFlag;

    public RadarView(Context context) {
        super(context);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint = new Paint(mPaint);
        mBgPaint.setStrokeWidth(3);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mSweepGradient = new SweepGradient(getWidth() / 2, getHeight() / 2, mColors, /*new float[]{0.5f, 1f}*/null);
        mMatrix = new Matrix();
        mMatrix.setRotate(mRotate, getWidth() / 2, getHeight() / 2);
        mSweepGradient.setLocalMatrix(mMatrix);
        mPaint.setShader(mSweepGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBgPaint.setColor(Color.GRAY);
        mBgPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, mBgPaint);

        mBgPaint.setColor(Color.WHITE);
        canvas.drawLine(getWidth() / 2 - RADIUS, getHeight() / 2, getWidth() / 2 + RADIUS, getHeight() / 2, mBgPaint);
        canvas.drawLine(getWidth() / 2, getHeight() / 2 - RADIUS, getWidth() / 2, getHeight() / 2 + RADIUS, mBgPaint);

        mBgPaint.setStyle(Paint.Style.STROKE);
        for (int i = 3; i > 0; i--) {
            int radius = RADIUS / 3 * i;
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mBgPaint);
        }

        mRotate += INTERVAL;
        mRotate = mRotate > 360 ? mRotate - 360 : mRotate;
        Log.e("onDraw", "mRotate = " + mRotate);
        mMatrix.setRotate(mRotate, getWidth() / 2, getHeight() / 2);
        mSweepGradient.setLocalMatrix(mMatrix);


        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, mPaint);

        if (mFlag) {
            postInvalidateDelayed(50);
        }
    }

    public void start() {
        if(!mFlag) {
            mFlag = true;
            invalidate();
        }
    }

    public void stop() {
        mFlag = false;
    }
}
