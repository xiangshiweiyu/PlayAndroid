package com.hxd.play.android;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.iv_welcome)
    ImageView ivWelcome;
    @BindView(R.id.cl_welcome)
    ConstraintLayout clWelcome;

    private Unbinder mUnbinder;
    private ValueAnimator colorAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mUnbinder = ButterKnife.bind(this);


        colorAnim = ObjectAnimator.ofInt(clWelcome, "backgroundColor", Color.RED, Color.BLUE);
        colorAnim.setDuration(1200);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

        Glide.with(this).load(R.mipmap.img_welcome).into(ivWelcome);

        mHandler.postDelayed(() -> mHandler.sendEmptyMessage(0), 1202);
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            WelcomeActivity.this.finish();

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }

        if (colorAnim != null && (colorAnim.isRunning() || colorAnim.isStarted())) {
            colorAnim.cancel();
        }
    }
}