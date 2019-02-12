package com.sj.skyblue.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.sj.skyblue.utils.PromptDialogUtil;
import com.sj.skyblue.utils.SystemUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.leefeng.promptlibrary.PromptDialog;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Sunj on 2018/10/30.
 */

public abstract class BaseActivity  extends SupportActivity {
    Unbinder unbinder;

    public abstract int getContentView();

    public View rootView;

    public PromptDialog promptDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = LayoutInflater.from(this).inflate(getContentView(), null);
        setContentView(rootView);
        unbinder = ButterKnife.bind(this);
        init(savedInstanceState);
        rootView.post(new Runnable() {
            @Override
            public void run() {
                initEvent();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        SystemUtil.setStickFullScreen(getWindow().getDecorView());
    }

    public void init(Bundle savedInstanceState) {
        promptDialog = new PromptDialog(this);
        PromptDialogUtil.getInstance().init(this);
    }

    public void initEvent() {
    }
    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

}
