package com.sj.module_lib.utils;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewGroup;

import com.sj.module_lib.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建时间: on 2018/3/29.
 * 创建人: 孙杰
 * 功能描述:Utils初始化相关
 */
public class Utils {

    private static Context context;
    private static Class mainActivity;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("u should init first");
    }

    /**
     * View获取Activity的工具
     *
     * @param view view
     * @return Activity
     */
    @NonNull
    public static Activity getActivity(View view) {
        Context context = view.getContext();

        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }

        throw new IllegalStateException("View " + view + " is not attached to an Activity");
    }

    /**
     * 全局获取String的方法
     *
     * @param id 资源Id
     * @return String
     */
    public static String getString(@StringRes int id) {
        return context.getResources().getString(id);
    }


    public static void setMainActivity(Class mainActivity) {
        Utils.mainActivity = mainActivity;
    }

    public static Class<?> getMainActivity() {
        return mainActivity;
    }

    public static <T> T checkNotNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }


    /**
     * 判断是否是手机号
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    /**
     * 调用第三方浏览器打开
     * @param context
     * @param url 要浏览的资源地址
     */
    public static  void openBrowser(Context context,String url){
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
        // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
        // 官方解释 : Name of the component implementing an activity that can display the intent
//        if (intent.resolveActivity(context.getPackageManager()) != null) {
//            final ComponentName componentName = intent.resolveActivity(context.getPackageManager());
//            // 打印Log   ComponentName到底是什么
//            context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
//        } else {
//            ToastUtils.showShortToast("请下载浏览器");
//        }
    }
}