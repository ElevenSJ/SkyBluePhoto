package com.sj.skyblue.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.DisplayUtils;
import com.sj.skyblue.R;
import com.sj.skyblue.manager.DialogManager;

/**
 * Created by Sunj on 2018/11/12.
 */

public class DialogUtil {

    AlertDialog dialog;
    private static final DialogUtil ourInstance = new DialogUtil();

    public static DialogUtil getInstance() {
        return ourInstance;
    }

    private DialogUtil() {
    }

    public AlertDialog showChooseDialog(final Context mContext, String[] items, final DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.CustomDialog);
        builder.setItems(items, listener);
        dialog = builder.create();
        dialog.show();
        dialog.getWindow().setLayout(DisplayUtils.dip2px(mContext, 400), ViewGroup.LayoutParams.WRAP_CONTENT);
        SystemUtil.setStickFullScreen(dialog.getWindow().getDecorView());
        return dialog;
    }

    public AlertDialog showBigViewDialog(final Context mContext, int resId, final ViewInterface listener) {
        // 获取布局
        final View view = View.inflate(mContext, resId, null);
        // 获取布局中的控件
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.CustomDialog);
        builder.setView(view);
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        if (listener != null && resId != 0) {
            listener.getChildView(dialog, view, resId);
        }
        dialog.getWindow().setLayout(DisplayUtils.dip2px(mContext, 1200), ViewGroup.LayoutParams.WRAP_CONTENT);
        SystemUtil.setStickFullScreen(dialog.getWindow().getDecorView());
        return dialog;
    }

    public AlertDialog showViewDialog(final Context mContext, int resId, final ViewInterface listener) {
        // 获取布局
        final View view = View.inflate(mContext, resId, null);
        // 获取布局中的控件
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.CustomDialog);
        builder.setView(view);
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        if (listener != null && resId != 0) {
            listener.getChildView(dialog, view, resId);
        }
        dialog.getWindow().setLayout(DisplayUtils.dip2px(mContext, 400), ViewGroup.LayoutParams.WRAP_CONTENT);
        SystemUtil.setStickFullScreen(dialog.getWindow().getDecorView());
        return dialog;
    }

    public AlertDialog showSureViewDialog(final Context mContext, int resId, final ViewInterface viewListener, final OnSureListener listener) {
        // 获取布局
        final View view = View.inflate(mContext, resId, null);
        // 获取布局中的控件
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.CustomDialog);
        builder.setView(view);
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        if (viewListener != null && resId != 0) {
            viewListener.getChildView(dialog, view, resId);
        }
        final View sureView = view.findViewById(R.id.bt_sure);
        if (sureView != null) {
            Logger.d("sureView != null");
            sureView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        Logger.d("sureView onClick callBack");
                        listener.callBack(sureView, dialog);
                    }
                }
            });
        }

        dialog.getWindow().setLayout(DisplayUtils.dip2px(mContext, 500), ViewGroup.LayoutParams.WRAP_CONTENT);
        SystemUtil.setStickFullScreen(dialog.getWindow().getDecorView());
        return dialog;
    }

    public interface OnSureListener {
        void callBack(View view, DialogInterface dialog);
    }

    public interface ViewInterface {
        void getChildView(AlertDialog dialog, View view, int layoutResId);
    }

    public void dissmiss() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog = null;
        }
    }
}
