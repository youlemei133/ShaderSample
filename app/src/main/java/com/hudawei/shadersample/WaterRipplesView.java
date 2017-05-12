package com.hudawei.shadersample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by hudawei on 2017/5/11.
 */

public class WaterRipplesView extends View {

    private Bitmap mBitmap;
    private Paint mPaint;
    private int RADIUS = 100;
    private RadialGradient radialGradient;
    private int[] mColors = new int[]{Color.TRANSPARENT, Color.GRAY, Color.TRANSPARENT, Color.GRAY, Color.TRANSPARENT};
    private float[] mPositions = new float[]{0.1f, 0.2f, 0.4f, 0.7f, 1.0f};
    float centerX, centerY;
    private ValueAnimator mAnimator;

    public WaterRipplesView(Context context) {
        super(context);
    }

    public WaterRipplesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.hsq);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        canvas.drawCircle(centerX, centerY, RADIUS, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            centerX = event.getX();
            centerY = event.getY();
            clickRipples();
        }
        return super.onTouchEvent(event);
    }

    private void clickRipples() {
        if (mAnimator != null && mAnimator.isRunning())
            mAnimator.cancel();
        mAnimator = ValueAnimator.ofFloat(0, 1)
                .setDuration(500);
        mAnimator.setInterpolator(new DecelerateInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                RADIUS = (int) (100 + 100 * value);
                radialGradient = new RadialGradient(centerX, centerY, RADIUS, mColors
                        , mPositions, Shader.TileMode.CLAMP);
                mPaint.setShader(radialGradient);
                mPaint.setAlpha((int) (255 * (1 - value)));
                postInvalidate();
            }
        });
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                RADIUS = 100;
            }
        });
        mAnimator.start();
    }
}
