package com.mobile.path.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.mobile.path.R;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * @author: douruanliang
 * @date: 2020/9/17
 */
public class RippleCircleView extends View {
    private RippleAnimationView mRippleAnimationView;
    public RippleCircleView(RippleAnimationView parent) {
        this(parent.getContext(), null);
        mRippleAnimationView = parent;
    }

    public RippleCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RippleCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float center = Math.min(getWidth(), getHeight())/2;

        canvas.drawCircle(center,center,center - RippleAnimationView.mStrokeWidth,
                mRippleAnimationView.getPaint());
    }
}
