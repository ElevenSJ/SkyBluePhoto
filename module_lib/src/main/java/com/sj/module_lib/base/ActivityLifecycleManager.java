package com.sj.module_lib.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.manager.ActivityStackManager;

/**
 * Created by Sunj on 2018/7/8.
 */

public class ActivityLifecycleManager  implements Application.ActivityLifecycleCallbacks{

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        ActivityStackManager.getActivityStackManager().addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ActivityStackManager.getActivityStackManager().finishActivity(activity);
    }
}
