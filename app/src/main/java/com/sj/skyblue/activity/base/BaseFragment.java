package com.sj.skyblue.activity.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.sj.skyblue.utils.PromptDialogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.leefeng.promptlibrary.PromptDialog;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Sunj on 2018/11/2.
 */

public abstract class BaseFragment extends SupportFragment {
    Unbinder unbinder;

    public abstract int getContentView();

    public View rootView;

    public PromptDialog promptDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("onCreate");
        init(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.i("onCreateView");
        rootView = LayoutInflater.from(_mActivity).inflate(getContentView(), null);
        unbinder = ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Logger.i("onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        promptDialog = new PromptDialog(_mActivity);
        PromptDialogUtil.getInstance().init(_mActivity);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    public void init(Bundle savedInstanceState) {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.i("onActivityCreated");
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        Logger.i("onEnterAnimationEnd");
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        Logger.i("onSupportVisible");
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        Logger.i("onSupportInVisible");
    }
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Logger.i("onLazyInitView");
    }

}
