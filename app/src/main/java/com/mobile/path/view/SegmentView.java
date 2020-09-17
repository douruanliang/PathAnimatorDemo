package com.mobile.path.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author: douruanliang
 * @date: 2020/9/17  自定义Path加载View
 */
public class SegmentView extends View {
    Paint mPaint;
    Path mDSTPath;
    Path mCirclePath;
    PathMeasure mPathMeasure;
    float mCurAnimValue ;
    public SegmentView(Context context) {
        this(context, null);
    }

    public SegmentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SegmentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.MAGENTA);

        mDSTPath = new Path();
        mCirclePath = new Path();
        mCirclePath.addCircle(100,100,50,Path.Direction.CW); //顺时针

        mPathMeasure = new PathMeasure(mCirclePath,true);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //0-1 百分必
                mCurAnimValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float length = mPathMeasure.getLength();
        float stop = length*mCurAnimValue;
        float start = (float) (stop - ((0.5 -Math.abs(mCurAnimValue -0.5)))*length);
        mDSTPath.reset();
        mPathMeasure.getSegment(start,stop,mDSTPath,true);
        canvas.drawPath(mDSTPath,mPaint);
    }
}
