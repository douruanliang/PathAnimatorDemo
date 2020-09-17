package com.mobile.path.view;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.mobile.path.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * @author: douruanliang
 * @date: 2020/9/17
 */
public class RippleAnimationView extends RelativeLayout {
    private Paint mPaint;
    private int radius;
    public static int  mStrokeWidth;
    private int rippleColor;
    private List<View> views = new ArrayList<>();
    private boolean animationRunning = false;

    public RippleAnimationView(Context context) {
      super(context);
    }

    public RippleAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Ripple);

        int rippleType = typedArray.getInt(R.styleable.Ripple_ripple_anim_fill_type,0);
        radius = typedArray.getInteger(R.styleable.Ripple_radius,60);
        mStrokeWidth = typedArray.getInteger(R.styleable.Ripple_strokeWidth,2);
        rippleColor = typedArray.getColor(R.styleable.Ripple_ripple_anim_color, ContextCompat.getColor(context,R.color.colorAccent));

        if (rippleType ==0){
            mPaint.setStyle(Paint.Style.FILL);
        }else{
            mPaint.setStyle(Paint.Style.STROKE);
        }

        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(rippleColor);

        int rippleDuration = 3500;
        int singleDelay = rippleDuration / 4;//间隔时间
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(radius + mStrokeWidth,
                radius + mStrokeWidth);
        params.addRule(CENTER_IN_PARENT);

        for (int i=0;i<2;i++){
            RippleCircleView rippleCircleView = new RippleCircleView(this);

            addView(rippleCircleView,params);

            views.add(rippleCircleView);
            PropertyValuesHolder aplhaHolder = PropertyValuesHolder.ofFloat("Alpha", 1, 0);
            PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofFloat("scaleX", 10);
            PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofFloat("scaleY", 10);

            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(
                    rippleCircleView, aplhaHolder, scaleXHolder, scaleYHolder);

            animator.setDuration(rippleDuration);
            animator.setStartDelay(i * singleDelay);
            animator.setRepeatMode(ObjectAnimator.RESTART);
            animator.setRepeatCount(ObjectAnimator.INFINITE);
            rippleCircleView.setTag(animator);

        }


    }
    public void startRippleAnimation() {
        if (!animationRunning) {
            for (View rippleView : views) {
                rippleView.setVisibility(VISIBLE);
                ((ObjectAnimator) rippleView.getTag()).start();
            }
            animationRunning = true;
        }

    }

    public void stopRippleAnimation() {
        if (animationRunning) {
            Collections.reverse(views);
            for (View rippleView : views) {
                rippleView.setVisibility(INVISIBLE);
                ((ObjectAnimator) rippleView.getTag()).end();
                ((ObjectAnimator) rippleView.getTag()).cancel();
            }
            animationRunning = false;
        }
    }

    public boolean isAnimationRunning() {
        return animationRunning;
    }

    public Paint getPaint() {
        return mPaint;
    }


}
