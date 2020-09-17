package com.mobile.path;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mobile.path.view.RippleAnimationView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView mSegmentView;
    RippleAnimationView mRippleAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSegmentView = findViewById(R.id.id_view);

        mRippleAnimationView = findViewById(R.id.id_ripple_view);
        mSegmentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRippleAnimationView.isAnimationRunning()) {
                    mRippleAnimationView.stopRippleAnimation();
                } else {
                    mRippleAnimationView.startRippleAnimation();
                }
            }
        });
    }
}