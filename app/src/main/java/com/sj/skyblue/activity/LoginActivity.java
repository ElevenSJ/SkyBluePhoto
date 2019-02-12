package com.sj.skyblue.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.http.BaseResponse;
import com.sj.module_lib.http.CommonCallback;
import com.sj.module_lib.http.ServerResultBack;
import com.sj.module_lib.utils.FileToolUtils;
import com.sj.module_lib.utils.SoftKeyboardUtil;
import com.sj.module_lib.utils.StringUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.skyblue.R;
import com.sj.skyblue.activity.base.BaseActivity;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.constant.PaymentConstants;
import com.sj.skyblue.entity.PayMethod;
import com.sj.skyblue.entity.ShopInfo;
import com.sj.skyblue.entity.UserInfoEntity;
import com.sj.skyblue.entity.payment.response.RspTerminal;
import com.sj.skyblue.http.API;
import com.sj.skyblue.http.PaymentAPI;
import com.sj.skyblue.http.base.BasePayResponse;
import com.sj.skyblue.http.base.PayServerResultBack;
import com.sj.skyblue.manager.SPManager;
import com.sj.skyblue.service.DisplayService;
import com.sj.skyblue.task.PayMethodSaveTask;
import com.sj.skyblue.task.UserInfoSaveTask;
import com.sj.skyblue.utils.DialogUtil;
import com.sj.skyblue.utils.SystemUtil;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;

/**
 * Created by Sunj on 2018/10/30.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;

    List<View> editViewList;

    AlertDialog bindDialog,activateDialog;

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        if (SPManager.getInstance().isLogined()) {
            toMainActivity();
        } else {
            editViewList = new ArrayList<>(2);
            editViewList.add(etAccount);
            editViewList.add(etPassword);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initEvent() {
        super.initEvent();
        etPassword
                .setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    public boolean onEditorAction(TextView v, int actionId,
                                                  KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_DONE
                                || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                            doLogin();
                            return true;
                        }
                        return false;
                    }
                });

        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .permission(Permission.READ_PHONE_STATE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Logger.i("同意授权");
                        doReadyJob();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Logger.i("拒绝授权");
                        ActivityCompat.finishAfterTransition(LoginActivity.this);
                    }
                }).start();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void doReadyJob() {
        if (!SPManager.getInstance().isBind()) {
            doDeviceBind();
        }else{
            getShopInfo();
//            if (!SPManager.getInstance().isActivated()) {
//                doPayActivate();
//            }
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void openDrawOverlays(){
        //没有悬浮窗权限m,去开启悬浮窗权限
        if (!Settings.canDrawOverlays(this)) {
            showSureDialog("请允许打开客显功能");
        } else {
            Intent intent = new Intent(this, DisplayService.class);
            startService(intent);
        }
    }

    private void doDeviceBind() {
        bindDialog = DialogUtil.getInstance().showViewDialog(LoginActivity.this, R.layout.dialog_device_activate, new DialogUtil.ViewInterface() {
            @Override
            public void getChildView(AlertDialog dialog, View view, int layoutResId) {
                final EditText etActivateCode = view.findViewById(R.id.et_activate_code);
                view.findViewById(R.id.bt_activate).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doBind(etActivateCode.getText().toString());
                    }
                });
            }
        });
        bindDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    bindDialog.dismiss();
                    ActivityCompat.finishAfterTransition(LoginActivity.this);
                }
                return false;
            }
        });
    }

    private void doBind(String code) {
        if (StringUtils.isEmpty(code)) {
            ToastUtils.showShortToast("请输入激活码");
            return;
        }
        bindDialog.dismiss();
        promptDialog.showLoading("正在绑定设备");
        API.deviceBindToShop(code, new CommonCallback() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onSuccess(String message) {
                promptDialog.showSuccessDelay("绑定成功", 500);
                SPManager.getInstance().setIsBind(true);
                getShopInfo();
            }

            @Override
            public void onFailed(String error_code, String error_message) {
                super.onFailed(error_code, error_message);
                promptDialog.dismiss();
                bindDialog.show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getShopInfo() {
        openDrawOverlays();
        String shopName =  SPManager.getInstance().getShopName();
        if (!StringUtils.isEmpty(shopName)){
            tvShopName.setText("门店："+shopName);
            return;
        }
        promptDialog.showLoading("正在查询门店信息");
        API.queryShopInfo(new ServerResultBack() {
            @Override
            public void onFinish() {
                super.onFinish();
                promptDialog.dismiss();
            }

            @Override
            public Type getType() {
                return new TypeReference<BaseResponse<ShopInfo>>() {
                }.getType();
            }

            @Override
            public void onSuccess(Object data) {
                ShopInfo shopInfo = (ShopInfo) data;
                SPManager.getInstance().setShopName(shopInfo.getName());
                tvShopName.setText("门店："+shopInfo.getName());
            }

        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.d(requestCode + "," + resultCode);
        if (requestCode == 100 && resultCode == 0) {
            if (Settings.canDrawOverlays(this)) {
                //客显
                Intent intent = new Intent(this, DisplayService.class);
                startService(intent);
            }
        }
    }

    private void doPayActivate() {
        activateDialog = DialogUtil.getInstance().showViewDialog(LoginActivity.this, R.layout.dialog_pay_activate, new DialogUtil.ViewInterface() {
            @Override
            public void getChildView(AlertDialog dialog, View view, int layoutResId) {
                final EditText etActivateCode = view.findViewById(R.id.et_activate_code);
                view.findViewById(R.id.bt_activate).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doActivate(etActivateCode.getText().toString());
                    }
                });
            }
        });
        activateDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    activateDialog.dismiss();
                    ActivityCompat.finishAfterTransition(LoginActivity.this);
                }
                return false;
            }
        });
    }

    private void doCheckin(final String phoneNum, final UserInfoEntity userInfoEntity) {
        promptDialog.showLoading("正在签到");
        PaymentAPI.checkin(new PayServerResultBack<BasePayResponse<RspTerminal>, RspTerminal>() {
            @Override
            public void onSuccess(RspTerminal data) {
                promptDialog.showSuccess("签到成功");
                SPManager.getInstance().savePayTerminal(data.getTerminal_sn(), data.getTerminal_key());
                SPManager.getInstance().saveLogined(true, phoneNum, userInfoEntity.getTokenid(), userInfoEntity.getShopId(), userInfoEntity.getName());
                new UserInfoSaveTask() {
                    @Override
                    protected void onPostExecute(Boolean aBoolean) {
                        super.onPostExecute(aBoolean);
                        Logger.i(!aBoolean ? "本地序列化用户信息失败" : "本地序列化用户信息成功");
                    }
                }.execute(userInfoEntity);
                FileToolUtils.fileCreate(FileToolUtils.getRootPath() + Constants.SDCARD_ROOT_PATH + File.separator + data.getTerminal_sn() + "_" + data.getTerminal_key() + PaymentConstants.checkinFileExt);
                toMainActivity();
            }

            @Override
            public void onError(String error_code, String error_message) {
                promptDialog.showWarnAlert(error_message, new PromptButton("确定", new PromptButtonListener() {

                    @Override
                    public void onClick(PromptButton promptButton) {
                        promptDialog.dismiss();
                    }
                }), true);
            }
        });
    }

    /**
     * 激活
     */
    private void doActivate(String code) {
        if (StringUtils.isEmpty(code)) {
            ToastUtils.showShortToast("请输入激活码");
            return;
        }
        activateDialog.dismiss();
        promptDialog.showLoading("正在激活");
        PaymentAPI.activate(code, new PayServerResultBack<BasePayResponse<RspTerminal>, RspTerminal>() {
            @Override
            public void onSuccess(RspTerminal data) {
                promptDialog.showSuccessDelay("激活成功", 500);
                SPManager.getInstance().setActivated(true);
                SPManager.getInstance().savePayTerminal(data.getTerminal_sn(), data.getTerminal_key());
                FileToolUtils.fileCreate(FileToolUtils.getRootPath() + Constants.SDCARD_ROOT_PATH + File.separator + SystemUtil.getDeviceSN() + PaymentConstants.activateFileExt);
            }

            @Override
            public void onError(String error_code, String error_message) {
//                promptDialog.showWarnAlert(error_message, new PromptButton("确定", new PromptButtonListener() {
//
//                    @Override
//                    public void onClick(PromptButton promptButton) {
//                        promptDialog.dismiss();
                        activateDialog.show();
//                    }
//                }), true);
            }
        });
    }

    private boolean checkEmpty() {
        boolean isOK = false;
        if (StringUtils.isEmpty(etAccount.getText().toString())) {
            ToastUtils.showShortToast("请输入用户名");
//        } else if (!Utils.isMobile(etAccount.getText().toString())) {
//            ToastUtils.showShortToast("用户名请输入手机号码");
        } else if (StringUtils.isEmpty(etPassword.getText().toString())) {
            ToastUtils.showShortToast("请输入密码");
        } else {
            isOK = true;
        }
        return isOK;
    }


    @OnClick({R.id.bt_login, R.id.layout_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                doLogin();
                break;
            case R.id.layout_content:
                SoftKeyboardUtil.hideSoftKeyboard(this, editViewList);
                break;
        }
    }

    private void doLogin() {
        SoftKeyboardUtil.hideSoftKeyboard(this, editViewList);
        if (checkEmpty()) {
            promptDialog.showLoading("正在登录");
            final String phoneNum = etAccount.getText().toString();
            String password = etPassword.getText().toString();
            API.appEmployeeLogin(phoneNum, password, new ServerResultBack() {
                @SuppressLint("StaticFieldLeak")
                @Override
                public void onSuccess(Object obj) {
                    UserInfoEntity userInfoEntity = (UserInfoEntity) obj;
//                    doCheckin(phoneNum, userInfoEntity);
                    SPManager.getInstance().saveLogined(true, phoneNum, userInfoEntity.getTokenid(), userInfoEntity.getShopId(), userInfoEntity.getName());
                    new UserInfoSaveTask() {
                        @Override
                        protected void onPostExecute(Boolean aBoolean) {
                            super.onPostExecute(aBoolean);
                            Logger.i(!aBoolean ? "本地序列化用户信息失败" : "本地序列化用户信息成功");
                        }
                    }.execute(userInfoEntity);
                    toMainActivity();
                }

                @Override
                public void onFailed(String error_code, String error_message) {
                    super.onFailed(error_code, error_message);
                    promptDialog.showError(error_message);
                }

                @Override
                public Type getType() {
                    return new TypeReference<BaseResponse<UserInfoEntity>>() {
                    }.getType();
                }
            });
        }
    }

    private void toMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showSureDialog(String msg) {
        promptDialog.showWarnAlert(msg, new PromptButton("确定", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton button) {
                promptDialog.dismiss();
                //没有悬浮窗权限,跳转申请
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 100);
            }

        }));
    }
}
