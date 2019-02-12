package com.sj.skyblue.activity.fragment.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.glide.ImageUtils;
import com.sj.module_lib.http.BaseResponse;
import com.sj.module_lib.http.CommonCallback;
import com.sj.module_lib.http.ServerResultBack;
import com.sj.module_lib.utils.SPUtils;
import com.sj.module_lib.utils.StringUtils;
import com.sj.skyblue.R;
import com.sj.skyblue.activity.BusinessActivity;
import com.sj.skyblue.activity.base.BaseFragment;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.constant.SPNameSpace;
import com.sj.skyblue.entity.AppointmentEntity;
import com.sj.skyblue.entity.PayMethod;
import com.sj.skyblue.entity.UserInfoEntity;
import com.sj.skyblue.entity.base.BaseDataList;
import com.sj.skyblue.http.API;
import com.sj.skyblue.manager.SPManager;
import com.sj.skyblue.manager.ThreadPoolManager;
import com.sj.skyblue.task.PayMethodSaveTask;
import com.sj.skyblue.task.UserInfoGetTask;
import com.sj.skyblue.utils.sunmi.AidlUtil;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by YoKeyword on 16/2/3.
 */
public class MainFragment extends BaseFragment {
    UserInfoEntity userInfoEntity;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.tv_welcome)
    TextView tvWelcome;
    @BindView(R.id.tv_work_order)
    TextView tvWorkOrder;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_main;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onLazyInitView(Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        new UserInfoGetTask() {
            @Override
            protected void onPostExecute(UserInfoEntity userInfoBean) {
                Logger.i(userInfoBean == null ? "读取本地序列化用户信息失败" : "读取本地序列化用户信息成功\n" + userInfoBean.toString());
                userInfoEntity = userInfoBean;
                if (userInfoEntity != null) {
                    initUser();
                }
            }
        }.execute();
        getShopMethed();
        AidlUtil.getInstance().initPrinter();
    }


    /**
     * 获取门店支付方式
     */
    private void getShopMethed() {
        API.queryShopPayMethod(new ServerResultBack() {
            @Override
            public Type getType() {
                return new TypeReference<BaseResponse<List<PayMethod>>>() {
                }.getType();
            }

            @Override
            public void onSuccess(Object data) {
                List<PayMethod> payMethodList = (List<PayMethod>) data;
                if (payMethodList!=null&&!payMethodList.isEmpty()) {
                    Logger.i("开始保存支付方式");
                    new PayMethodSaveTask() {
                        @Override
                        protected void onPostExecute(Boolean aBoolean) {
                            super.onPostExecute(aBoolean);
                            Logger.i(!aBoolean ? "本地序列化支付方式成功" : "本地序列化支付方式成功");
                        }
                    }.execute(payMethodList);
                }
            }
        });
    }
    /**
     * 初始化页面数据
     */
    private void initUser() {
        ImageUtils.loadImageWithError(userInfoEntity.getHeadPortrait(),R.drawable.img_staff_women,imgHead);
        tvWelcome.setText("欢迎您，" + userInfoEntity.getName());
        if (SPManager.getInstance().isWorkOpen()){
            initWorkOrder(SPManager.getInstance().getWorkOrder());
        }
    }

    /**
     * 初始化班次
     */
    private void initWorkOrder(String data) {
        tvWorkOrder.setVisibility(StringUtils.isEmpty(data)?View.GONE:View.VISIBLE);
        tvWorkOrder.setText("当前班次:" + data);
    }

    @OnClick({R.id.img_head, R.id.tv_setting, R.id.tv_slip_code, R.id.tv_shop, R.id.tv_appointment_list, R.id.tv_work_shift, R.id.tv_word_open, R.id.img_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_head:
                ThreadPoolManager.getInstance().executeTask(new Runnable() {
                    @Override
                    public void run() {
                        AidlUtil.getInstance().printText("测试打印功能1", 16, false, false);
                        AidlUtil.getInstance().cutPaper();
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {

                        }
                    }
                });
                break;
            case R.id.tv_setting:
                toBusiness(BusinessActivity.SETTING);
                break;
            case R.id.tv_slip_code:
                toBusiness(BusinessActivity.SLIP);
                break;
            case R.id.tv_shop:
                toBusiness(BusinessActivity.SHOP);
                break;
            case R.id.tv_appointment_list:
                if (!SPManager.getInstance().isWorkOpen()){
                    promptDialog.showInfo("您还没有开班哦！");
                    return;
                }
                toBusiness(BusinessActivity.ORDER);
                break;
            case R.id.tv_work_shift:
                if (!SPManager.getInstance().isWorkOpen()){
                    promptDialog.showInfo("您还没有开班哦！");
                    return;
                }
                showSureDialog(view.getId(), "是否确认交班");
                break;
            case R.id.tv_word_open:
                if (SPManager.getInstance().isWorkOpen()){
                    promptDialog.showInfo("您已开班，如需重请选择交班");
                    return;
                }
                showSureDialog(view.getId(), "是否确认开班");
                break;
            case R.id.img_exit:
                showSureDialog(view.getId(), "是否确认退出系统");
                break;
        }
    }

    private void toBusiness(int index) {
        Intent intent = new Intent(_mActivity, BusinessActivity.class);
        intent.putExtra("index",index);
        startActivity(intent);
    }

    private void showSureDialog(final int id, String msg) {
        //按钮的定义，创建一个按钮的对象
        final PromptButton confirm = new PromptButton("确定", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton button) {
                promptDialog.dismiss();
                switch (id) {
                    case R.id.tv_work_shift:
                        promptDialog.showLoading("正在交班");
                        API.updateEmployeeWorkStatus(Constants.STATUS_WORK_SHIFT, new CommonCallback() {

                            @Override
                            public void onSuccess(String message) {
                                promptDialog.showSuccessDelay("交班成功", 500);
                                SPManager.getInstance().exitWork();
                                initWorkOrder(null);
                            }

                            @Override
                            public void onFinish() {
                                super.onFinish();
                                promptDialog.dismiss();
                            }
                        });
                        break;
                    case R.id.tv_word_open:
                        promptDialog.showLoading("正在开班");
                        API.updateEmployeeWorkStatus(Constants.STATUS_WORK_OPRN, new ServerResultBack() {
                            @Override
                            public void onSuccess(Object data) {
                                promptDialog.showSuccessDelay("开班成功", 500);
                                SPManager.getInstance().setWorkOpen((String)data);
                                initWorkOrder((String) data);
                            }

                            @Override
                            public Type getType() {
                                return new TypeReference<BaseResponse<String>>() {
                                }.getType();
                            }

                            @Override
                            public void onFinish() {
                                super.onFinish();
                                promptDialog.dismiss();
                            }
                        });
                        break;
                    case R.id.img_exit:
                        promptDialog.showLoading("正在退出");
                        if (SPManager.getInstance().isWorkOpen()){
                            API.updateEmployeeWorkStatus(Constants.STATUS_WORK_CLOSE, new CommonCallback() {
                                @Override
                                public void onSuccess(String message) {
                                    SPManager.getInstance().exit();
                                    _mActivity.finish();
                                }

                                @Override
                                public void onFinish() {
                                    super.onFinish();
                                    promptDialog.dismiss();
                                }
                            });
                        }else{
                            SPManager.getInstance().exit();
                            _mActivity.finish();
                        }
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

}
