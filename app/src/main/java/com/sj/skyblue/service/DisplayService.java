package com.sj.skyblue.service;

import android.app.Presentation;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.view.WindowManager;

import com.orhanobut.logger.Logger;
import com.sj.skyblue.presentation.DifferentDefaultDislay;
import com.sj.skyblue.presentation.DifferentOrderDislay;
import com.sj.skyblue.presentation.DifferentRegisterDislay;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.event.PresentationChangeEvent;
import com.sj.skyblue.manager.ScreenManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Sunj on 2018/11/16.
 */

public class DisplayService extends Service {
    DifferentDefaultDislay mDefaultPresentation;
    DifferentRegisterDislay mRegisterPresentation;
    DifferentOrderDislay mOrderPresentation;

    private int presentationIndex = -1;
    Presentation currentPresentation;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        showView(Constants.CUSTOMER_DISPLAY_DEFAULT);
    }

    private void showView(int index) {
        if (ScreenManager.getInstance().getDisplays().length < 2) {
            Logger.i("this device has only one display screen");
            return;
        }
        if (currentPresentation != null && presentationIndex == index) {
            Logger.i("not need to change display");
            return;
        }
        switch (index) {
            case Constants.CUSTOMER_DISPLAY_DEFAULT:
                if (null == mDefaultPresentation) {
                    mDefaultPresentation = new DifferentDefaultDislay(this.getApplication(), ScreenManager.getInstance().getDisplays()[1]);// displays[1]是副屏
                    mDefaultPresentation.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                }
                dismissBeforePresentation(mDefaultPresentation);
                mDefaultPresentation.show();
                break;
            case Constants.CUSTOMER_DISPLAY_REGISTER:
                if (null == mRegisterPresentation) {
                    mRegisterPresentation = new DifferentRegisterDislay(this.getApplication(), ScreenManager.getInstance().getDisplays()[1]);// displays[1]是副屏
                    mRegisterPresentation.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                }
                dismissBeforePresentation(mRegisterPresentation);
                mRegisterPresentation.show();
                break;
            case Constants.CUSTOMER_DISPLAY_ORDER:
                if (null == mOrderPresentation) {
                    mOrderPresentation = new DifferentOrderDislay(this.getApplication(), ScreenManager.getInstance().getDisplays()[1]);// displays[1]是副屏
                    mOrderPresentation.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                }
                dismissBeforePresentation(mOrderPresentation);
                mOrderPresentation.show();
                break;
        }
        presentationIndex = index;
    }
    private void dismissBeforePresentation(final Presentation lastPresentation){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentPresentation != null) {
                    currentPresentation.dismiss();
                }
                currentPresentation = lastPresentation;
            }
        }, 200);
    }

    @Subscribe
    public void presentationChange(PresentationChangeEvent event) {
        if (null == event) {
            Logger.e("PresentationChangeEvent is null");
            return;
        }
        showView(event.position);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
