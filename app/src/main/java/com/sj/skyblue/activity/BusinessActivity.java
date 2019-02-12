package com.sj.skyblue.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.http.CommonCallback;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.skyblue.R;
import com.sj.skyblue.activity.base.BaseActivity;
import com.sj.skyblue.activity.fragment.business.order.OrderMainFragment;
import com.sj.skyblue.activity.fragment.business.setting.SettingMainFragment;
import com.sj.skyblue.activity.fragment.business.shop.ShopFragment;
import com.sj.skyblue.activity.fragment.business.slipcode.SlipCodeFragment;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.UserInfoEntity;
import com.sj.skyblue.http.API;
import com.sj.skyblue.manager.SPManager;
import com.sj.skyblue.task.UserInfoGetTask;
import com.sj.skyblue.utils.DialogUtil;

import butterknife.BindView;
import butterknife.OnClick;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by Sunj on 2018/11/3.
 */

public class BusinessActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.tv_work_order)
    TextView tvWorkOrder;
    @BindView(R.id.tv_welcome)
    TextView tvWelcome;
    @BindView(R.id.et_order_num)
    EditText etOrderNum;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    TextView tvAddOrder;

    PromptDialog promptDialog;

    UserInfoEntity userInfoEntity;

    private int currentIndex = ORDER;


    public static final int ORDER = 0;
    public static final int SHOP = 1;
    public static final int SLIP = 2;
    public static final int SETTING = 3;

    private SupportFragment[] mFragments = new SupportFragment[4];

    @Override
    public int getContentView() {
        return R.layout.activity_business;
    }

    @OnClick({R.id.img_exit, R.id.img_nav, R.id.img_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_exit:
                showSureDialog(view.getId(), "是否确认退出系统");
                break;
            case R.id.img_nav:
                onOpenOrCloseDrawer();
                break;
            case R.id.img_icon:
                ActivityCompat.finishAfterTransition(this);
                break;
        }
    }
    private void showSureDialog(final int id, String msg) {
        //按钮的定义，创建一个按钮的对象
        final PromptButton confirm = new PromptButton("确定", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton button) {
                promptDialog.dismiss();
                switch (id) {
                    case R.id.img_exit:
                        promptDialog.showLoading("正在退出");
                            API.updateEmployeeWorkStatus(Constants.STATUS_WORK_CLOSE, new CommonCallback() {
                                @Override
                                public void onSuccess(String message) {
                                    exit();
                                }

                                @Override
                                public void onFinish() {
                                    super.onFinish();
                                    promptDialog.dismiss();
                                }
                            });
                        break;
                }
            }
        });
        confirm.setTextColor(Color.parseColor("#DAA520"));
        confirm.setFocusBacColor(Color.parseColor("#FAFAD2"));
        confirm.setDelyClick(true);//点击后，是否再对话框消失后响应按钮的监听事件
        promptDialog.showWarnAlert(msg, new PromptButton("取消", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton button) {
                promptDialog.dismiss();
            }
        }), confirm);
    }

    private void exit() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("LoginOut",true);
        startActivity(intent);
        ActivityCompat.finishAfterTransition(this);
    }

    /**
     * 打开抽屉
     */
    public void onOpenOrCloseDrawer() {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.openDrawer(GravityCompat.START);
        } else {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("index", currentIndex);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("index", ORDER);
        } else {
            currentIndex = getIntent().getIntExtra("index", ORDER);
        }
        promptDialog = new PromptDialog(this);
        SupportFragment firstFragment = findFragment(OrderMainFragment.class);
        if (firstFragment == null) {
            mFragments[ORDER] = OrderMainFragment.newInstance();
            mFragments[SHOP] = ShopFragment.newInstance();
            mFragments[SLIP] = SlipCodeFragment.newInstance();
            mFragments[SETTING] = SettingMainFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, currentIndex,
                    mFragments[ORDER],
                    mFragments[SHOP],
                    mFragments[SLIP],
                    mFragments[SETTING]);
        } else {
            // 这里我们需要拿到mFragments的引用
            mFragments[ORDER] = firstFragment;
            mFragments[SHOP] = findFragment(ShopFragment.class);
            mFragments[SLIP] = findFragment(SlipCodeFragment.class);
            mFragments[SETTING] = findFragment(SettingMainFragment.class);
        }

    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void initEvent() {
        super.initEvent();
        new UserInfoGetTask() {
            @Override
            protected void onPostExecute(UserInfoEntity userInfoBean) {
                Logger.i(userInfoBean == null ? "读取本地序列化用户信息失败" : "读取本地序列化用户信息成功\n" + userInfoBean.toString());
                userInfoEntity = userInfoBean;
                if (userInfoEntity != null) {
                    updateTitle();
                }
            }
        }.execute();

        navView.setNavigationItemSelectedListener(this);
        switch (currentIndex) {
            case ORDER:
                navView.setCheckedItem(R.id.nav_order);
                break;
            case SHOP:
                navView.setCheckedItem(R.id.nav_shop);
                break;
            case SLIP:
                navView.setCheckedItem(R.id.nav_slip_code);
                break;
            case SETTING:
                navView.setCheckedItem(R.id.nav_setting);
                break;
        }

//        LinearLayout llNavHeader = (LinearLayout) navView.getHeaderView(0);
//        tvAddOrder = llNavHeader.findViewById(R.id.tv_add_order);
//        llNavHeader.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.closeDrawer(GravityCompat.START);
//                drawerLayout.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        ((OrderMainFragment)mFragments[ORDER]).createOrder();
//                    }
//                }, 250);
//            }
//        });

    }

    /**
     * 初始化页面数据
     */
    private void updateTitle() {
        tvWelcome.setText("欢迎您，" + userInfoEntity.getName());
        tvWorkOrder.setText("当前班次:" + SPManager.getInstance().getWorkOrder());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        drawerLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    ActivityCompat.finishAfterTransition(BusinessActivity.this);
                } else if (id == R.id.nav_order) {
                    showHideFragment(mFragments[ORDER], mFragments[currentIndex]);
                    currentIndex = ORDER;
                } else if (id == R.id.nav_shop) {
                    showHideFragment(mFragments[SHOP], mFragments[currentIndex]);
                    currentIndex = SHOP;
                } else if (id == R.id.nav_slip_code) {
                    showHideFragment(mFragments[SLIP], mFragments[currentIndex]);
                    currentIndex = SLIP;
                } else if (id == R.id.nav_setting) {
                    showHideFragment(mFragments[SETTING], mFragments[currentIndex]);
                    currentIndex = SETTING;
                }
            }
        }, 300);

        return true;
    }

    @Override
    public void onBackPressedSupport() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                pop();
            } else {
                ActivityCompat.finishAfterTransition(this);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DialogUtil.getInstance().dissmiss();
    }
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }
}
