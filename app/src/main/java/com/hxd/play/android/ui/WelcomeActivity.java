package com.hxd.play.android.ui;

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
import com.hxd.play.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.iv_welcome)
    ImageView ivWelcome;
    @BindView(R.id.cl_welcome)
    ConstraintLayout clWelcome;

    private Unbinder mUnBinder;
    private ValueAnimator mColorAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mUnBinder = ButterKnife.bind(this);


        mColorAnim = ObjectAnimator.ofInt(clWelcome, "backgroundColor", Color.RED, Color.BLUE);
        mColorAnim.setDuration(1200);
        mColorAnim.setEvaluator(new ArgbEvaluator());
        mColorAnim.setRepeatCount(ValueAnimator.INFINITE);
        mColorAnim.setRepeatMode(ValueAnimator.REVERSE);
        mColorAnim.start();

        Glide.with(this).load(R.mipmap.img_welcome).into(ivWelcome);

        mHandler.postDelayed(() -> mHandler.sendEmptyMessage(0), 1202);

        //        mHandler.postDelayed(new Runnable() {
        //            @Override
        //            public void run() {
        //                mHandler.sendEmptyMessage(0);
        //            }
        //        }, 1200);
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

        if (mUnBinder != null) {
            mUnBinder.unbind();
            mUnBinder = null;
        }

        if (mColorAnim != null && (mColorAnim.isRunning() || mColorAnim.isStarted())) {
            mColorAnim.cancel();
        }

        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }
}