package com.sj.skyblue.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.DisplayUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.skyblue.R;
import com.sj.skyblue.activity.adapter.AddServiceRyvAdapter;
import com.sj.skyblue.activity.adapter.BindEmployeeRyvAdapter;
import com.sj.skyblue.activity.adapter.itemdecoration.SpaceItemDecoration;
import com.sj.skyblue.entity.CashierInfo;
import com.sj.skyblue.entity.EmployeeBase;
import com.sj.skyblue.entity.GoodEntity;
import com.sj.skyblue.entity.ServiceEntity;

import java.util.ArrayList;
import java.util.List;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by Sunj on 2018/11/12.
 */

public class BindEmployeeDialogView implements View.OnClickListener {
    ImageView imgClose;
    EasyRecyclerView rylView;
    Button btSure;
    Button btCancle;

    BindEmployeeRyvAdapter mAdapter;
    final int SPANCOUNT = 6;

    private Activity activity;
    private View rootView;
    private AlertDialog dialog;


    private int mCurrentPosition = -1;

    IItemSelectLisenter iItemSelectLisenter;

    EmployeeBase employeeBase;

    public BindEmployeeDialogView(Activity activity, AlertDialog dialog, View view) {
        this.activity = activity;
        this.rootView = view;
        this.dialog = dialog;
    }

    public void initModule(List<CashierInfo> cashierInfos, List<EmployeeBase> shopManagers) {
        if (rootView == null) {
            Logger.e("not root exist");
            return;
        }
        imgClose = rootView.findViewById(R.id.img_close);
        rylView = rootView.findViewById(R.id.ryl_view);
        btSure = rootView.findViewById(R.id.bt_sure);
        btCancle = rootView.findViewById(R.id.bt_cancle);

        imgClose.setOnClickListener(this);
        btCancle.setOnClickListener(this);
        btSure.setOnClickListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, SPANCOUNT, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(gridLayoutManager);
        rylView.addItemDecoration(new SpaceItemDecoration(DisplayUtils.dip2px(activity, 20), 0, true, SpaceItemDecoration.GRIDLAYOUT));
        mAdapter = new BindEmployeeRyvAdapter(activity);
        rylView.setAdapter(mAdapter);
        mAdapter.addAll(cashierInfos);
        mAdapter.addAll(shopManagers);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (mCurrentPosition != -1) {
                    mAdapter.getItem(mCurrentPosition).setSelected(false);
                    mAdapter.notifyItemChanged(mCurrentPosition);
                }
                if (mCurrentPosition != position) {
                    mAdapter.getItem(position).setSelected(!mAdapter.getItem(position).isSelected());
                    mAdapter.notifyItemChanged(position);
                }
                if (mAdapter.getItem(position).isSelected()) {
                    employeeBase = mAdapter.getItem(position);
                    mCurrentPosition = position;
                } else {
                    employeeBase = null;
                    mCurrentPosition = -1;
                }
            }
        });

    }

    private boolean checkEmpty() {
        boolean isOK = false;
        if (employeeBase == null) {
            ToastUtils.showShortToast("请选择一位前台人员");
        } else {
            isOK = true;
        }
        return isOK;
    }

    public void setIItemSelectLisenter(IItemSelectLisenter selectLisenter) {
        this.iItemSelectLisenter = selectLisenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_close:
            case R.id.bt_cancle:
                dissmiss();
                break;
            case R.id.bt_sure:
                if (checkEmpty()) {
                    if (iItemSelectLisenter != null) {
                        iItemSelectLisenter.callback(employeeBase);
                    }
                    dissmiss();
                }
                break;
        }
    }

    private void dissmiss() {
        if (dialog != null) {
            dialog.dismiss();
            employeeBase = null;
            mCurrentPosition = -1;
        }
    }


    public interface IItemSelectLisenter {
        void callback(EmployeeBase employeeBase);
    }

}
