package com.sj.skyblue.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.sj.module_lib.utils.Utils;
import com.sj.skyblue.R;
import com.sj.skyblue.activity.base.BaseActivity;
import com.sj.skyblue.activity.fragment.main.MainFragment;
import com.sj.skyblue.manager.SPManager;
import com.sj.skyblue.service.DisplayService;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by Sunj on 2018/11/1.
 */

public class MainActivity extends BaseActivity {

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Utils.setMainActivity(this.getClass());
        SupportFragment fragment = findFragment(MainFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance(),true, true);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initEvent() {
        super.initEvent();
        if (Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(this, DisplayService.class);
            startService(intent);
        }
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                ActivityCompat.finishAfterTransition(this);
            } else {
                TOUCH_TIME = System.currentTimeMillis();
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //互斥登录，退出当前设备
        if (intent != null) {
            if (intent.getBooleanExtra("LoginOut", false)) {
                back2Login();
            }
        }
    }

    public void back2Login() {
        SPManager.getInstance().exit();
        Intent i = new Intent();
        i.setClass(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }
}
