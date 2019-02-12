package com.sj.skyblue.utils;

import android.view.View;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.DeviceUtils;
import com.sj.module_lib.utils.StringUtils;
import com.sj.module_lib.utils.Utils;

import java.lang.reflect.Method;

/**
 * Created by Sunj on 2018/10/30.
 */

public class SystemUtil {

    public static void setStickFullScreen(View view) {
        int systemUiVisibility = view.getSystemUiVisibility();
        int flags = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        systemUiVisibility |= flags;
        view.setSystemUiVisibility(systemUiVisibility);
    }

    public static String getDeviceSN() {
        try {
            Class c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            String sn = (String) get.invoke(c, "ro.serialno");
            if (StringUtils.isEmpty(sn)){
                sn = DeviceUtils.getUniqueId(Utils.getContext());
            }
            return sn;
        } catch (Exception e) {
            e.printStackTrace();
            return DeviceUtils.getUniqueId(Utils.getContext());
        }
    }

}
