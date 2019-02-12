package com.sj.skyblue.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.DisplayUtils;
import com.sj.module_lib.utils.StringUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.skyblue.R;
import com.sj.skyblue.activity.adapter.ServiceRyvAdapter;
import com.sj.skyblue.activity.adapter.itemdecoration.SpaceItemDecoration;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.ServiceEntity;
import com.sj.skyblue.event.PresentationChangeEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


/**
 * Created by Sunj on 2018/11/12.
 */

public class OrderCreateDialogView implements View.OnClickListener {
    ImageView imgClose;
    EditText etPhone;
    EasyRecyclerView rylView;
    Button btSure;
    Button btCancle;

    ServiceRyvAdapter mAdapter;
    final int SPANCOUNT = 8;

    private Activity activity;
    private View rootView;
    private AlertDialog dialog;

    private int mCurrentPosition = -1;

    IServiceItemSelectLisenter iItemSelectLisenter;

    ServiceEntity serviceEntity;
    String time;

    public OrderCreateDialogView(Activity activity, AlertDialog dialog, View view) {
        this.activity = activity;
        this.rootView = view;
        this.dialog = dialog;
        EventBus.getDefault().post(new PresentationChangeEvent(Constants.CUSTOMER_DISPLAY_REGISTER));
    }

    public void initModule(String time,List<ServiceEntity> services) {
        if (rootView == null) {
            Logger.e("not root exist");
            return;
        }
        this.time = time;
        imgClose = rootView.findViewById(R.id.img_close);
        etPhone = rootView.findViewById(R.id.et_phone);
        rylView = rootView.findViewById(R.id.ryl_view);
        btSure = rootView.findViewById(R.id.bt_sure);
        btCancle = rootView.findViewById(R.id.bt_cancle);

        imgClose.setOnClickListener(this);
        btCancle.setOnClickListener(this);
        btSure.setOnClickListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, SPANCOUNT, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(gridLayoutManager);
        rylView.addItemDecoration(new SpaceItemDecoration(DisplayUtils.dip2px(activity, 20), 0, true, SpaceItemDecoration.GRIDLAYOUT));
        mAdapter = new ServiceRyvAdapter(activity);
        rylView.setAdapter(mAdapter);
        mAdapter.addAll(services);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                serviceEntity = mAdapter.getItem(position);
                if (mCurrentPosition != -1) {
                    mAdapter.getItem(mCurrentPosition).setSelected(false);
                }
                mAdapter.getItem(position).setSelected(true);
                mAdapter.notifyItemChanged(mCurrentPosition);
                mAdapter.notifyItemChanged(position);
                mCurrentPosition = position;
            }
        });
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
            }
        });
    }

    private boolean checkEmpty() {
        boolean isOK = false;
        if (StringUtils.isEmpty(etPhone.getText().toString())) {
            ToastUtils.showShortToast("请输入用手机号码或用户名");
//        } else if (!Utils.isMobile(etPhone.getText().toString())) {
//            ToastUtils.showShortToast("请输入正确的手机号码");
        } else if (serviceEntity==null) {
            ToastUtils.showShortToast("请选择一项服务");
        } else {
            isOK = true;
        }
        return isOK;
    }

    public void setIServiceItemSelectLisenter(IServiceItemSelectLisenter itemSelectLisenter) {
        this.iItemSelectLisenter = itemSelectLisenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_close:
            case R.id.bt_cancle:
                dissmiss();
                break;
            case R.id.bt_sure:
                if (checkEmpty()){
                    if (iItemSelectLisenter != null) {
                            iItemSelectLisenter.callback(time,etPhone.getText().toString(),serviceEntity);
                    }
                   dissmiss();
                }
                break;
        }
    }

    private void dissmiss(){
        if (dialog != null) {
            dialog.dismiss();
            serviceEntity = null;
            mCurrentPosition = -1;
        }
        EventBus.getDefault().post(new PresentationChangeEvent(Constants.CUSTOMER_DISPLAY_DEFAULT));
    }
    public interface IServiceItemSelectLisenter {
        void callback(String time,String phoneNum,ServiceEntity resEntity);
    }
    public void hideKeyBoard() {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(etPhone.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
