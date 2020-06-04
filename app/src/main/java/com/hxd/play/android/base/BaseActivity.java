package com.hxd.play.android.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * CreateTime: 2020/6/4  10:15
 * Author: hxd
 * Content:
 * UpdateTime:
 * UpdateName;
 * UpdateContent:
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());

        initView(savedInstanceState);
    }


    /**
     * 获关联界面位置
     *
     * @return 界面id
     */
    protected abstract int layoutId();

    /**
     * 初始化控件
     *
     * @param savedInstanceState 绑定数据
     */
    protected void initView(Bundle savedInstanceState) {

    }

}
