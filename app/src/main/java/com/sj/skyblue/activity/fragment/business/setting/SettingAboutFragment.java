package com.sj.skyblue.activity.fragment.business.setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.http.BaseResponse;
import com.sj.module_lib.http.ServerResultBack;
import com.sj.module_lib.utils.APPUtils;
import com.sj.module_lib.utils.StringUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.module_lib.utils.Utils;
import com.sj.skyblue.R;
import com.sj.skyblue.activity.base.BaseFragment;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.AppUpdateInfo;
import com.sj.skyblue.entity.GoodServiceEntity;
import com.sj.skyblue.entity.post.PayBackBean;
import com.sj.skyblue.http.API;
import com.sj.skyblue.utils.DialogUtil;
import com.sj.skyblue.utils.sunmi.AidlUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by Sunj on 2018/11/4.
 */

public class SettingAboutFragment extends BaseFragment {


    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.bt_update)
    Button btUpdate;

    public static SettingAboutFragment newInstance() {
        SettingAboutFragment fragment = new SettingAboutFragment();
        return fragment;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_setting_about;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        StringBuilder appInfoBuilder = new StringBuilder();
        appInfoBuilder.append("应用名称：" + getString(R.string.app_name));
        appInfoBuilder.append("\n");
        appInfoBuilder.append("应用包名：" + _mActivity.getPackageName());
        appInfoBuilder.append("\n");
        appInfoBuilder.append("版本名称：" + APPUtils.getVerName(_mActivity));
        appInfoBuilder.append("\n");
        appInfoBuilder.append("版  本  号：" + APPUtils.getVersionCode(_mActivity));
        tvInfo.setText(appInfoBuilder.toString());
    }

    @OnClick(R.id.bt_update)
    public void onViewClicked() {
        promptDialog.showLoading("正在获取更新");
        API.queryApp(APPUtils.getVersionCode(_mActivity), new ServerResultBack() {
            @Override
            public Type getType() {
                return new TypeReference<BaseResponse<AppUpdateInfo>>() {
                }.getType();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                promptDialog.dismiss();
            }

            @Override
            public void onSuccess(Object data) {
                final AppUpdateInfo appUpdateInfo = (AppUpdateInfo) data;
                if (null!=appUpdateInfo){
                    DialogUtil.getInstance().showSureViewDialog(_mActivity, R.layout.dialog_app_update, new DialogUtil.ViewInterface() {
                        @Override
                        public void getChildView(AlertDialog dialog, View view, int layoutResId) {
                            StringBuilder appInfoBuilder = new StringBuilder();
                            appInfoBuilder.append("应用名称：" + appUpdateInfo.getName());
                            appInfoBuilder.append("\n");
                            appInfoBuilder.append("应用平台：" + appUpdateInfo.getPlatform());
                            appInfoBuilder.append("\n");
                            appInfoBuilder.append("版本名称：" + appUpdateInfo.getVersion());
                            appInfoBuilder.append("\n");
                            appInfoBuilder.append("更新时间：" +appUpdateInfo.getUpdateTime());
                            TextView tvAppInfo = view.findViewById(R.id.tv_info);
                            tvAppInfo.setText(appInfoBuilder.toString());
                        }
                    }, new DialogUtil.OnSureListener() {
                        @Override
                        public void callBack(View view, final DialogInterface dialog) {
                            dialog.dismiss();
                            Utils.openBrowser(_mActivity,appUpdateInfo.getUrl());
                        }
                    });
                }
            }
        });
    }
}