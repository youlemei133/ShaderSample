package com.hudawei.shadersample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by hudawei on 2017/5/11.
 * 跑马灯效果
 */

public class MarqueeView extends AppCompatTextView {
    private float[] mPositions;
    private int[] mColors;
    private LinearGradient mLinearGradient;
    private Matrix mMatrix;
    private int INTERVAL = 20;
    private int OFFSET;
    private int mSingleWidth;
    private Rect mRect;
    private ShapeDrawable mShapeDrawable;

    public MarqueeView(Context context) {
        super(context);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mColors = new int[3];
        mColors[0] = Color.CYAN;
        mColors[1] = Color.WHITE;
        mColors[2] = Color.CYAN;

        mPositions = new float[3];
        mPositions[0] = 0;
        mPositions[1] = 0.3f;
        mPositions[2] = 1.0f;

        mMatrix = new Matrix();
        mRect = new Rect();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        String text = getText().toString();
        int width = (int) getPaint().measureText(text);
        mSingleWidth = width / text.length();
        mLinearGradient = new LinearGradient(-mSingleWidth, 0, mSingleWidth * 2, 0, mColors, mPositions,
                Shader.TileMode.CLAMP);
        getPaint().setShader(mLinearGradient);

//        mShapeDrawable = new ShapeDrawable(new RectShape());
//        mShapeDrawable.getPaint().setShader(mLinearGradient);
//        getLineBounds(0,mRect);
//        mShapeDrawable.setBounds(mRect);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (OFFSET > getWidth() - mSingleWidth || OFFSET < 0) {
            INTERVAL = -INTERVAL;
        }
        OFFSET += INTERVAL;
        mMatrix.setTranslate(OFFSET, 0);
        mLinearGradient.setLocalMatrix(mMatrix);

        postInvalidateDelayed(50);
    }
}
