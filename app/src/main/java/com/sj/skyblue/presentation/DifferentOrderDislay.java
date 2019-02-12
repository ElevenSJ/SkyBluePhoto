package com.sj.skyblue.presentation;

import android.app.Presentation;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Display;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.CalculateUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.skyblue.R;
import com.sj.skyblue.activity.adapter.AppointmentRyvAdapter;
import com.sj.skyblue.activity.adapter.DispayOrderRyvAdapter;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.order.OrderPhoto;
import com.sj.skyblue.entity.order.OrderPhotoService;
import com.sj.skyblue.entity.order.OrderProduct;
import com.sj.skyblue.entity.order.OrderProductService;
import com.sj.skyblue.entity.order.PhotoServiceItem;
import com.sj.skyblue.entity.order.base.OrderBase;
import com.sj.skyblue.entity.order.base.OrderServiceBase;
import com.sj.skyblue.event.OrderShowEvent;
import com.sj.skyblue.manager.handler.OrderDetailHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunj on 2018/11/16.
 */

public class DifferentOrderDislay extends Presentation {
    EasyRecyclerView rylView;
    DispayOrderRyvAdapter mAdapter;

    TextView tvTotalPrice;
    TextView tvPaidPrice;
    TextView tvComboPrice;
    TextView tvDiscountPrice;
    TextView tvNeedPrice;

    public DifferentOrderDislay(Context outerContext, Display display) {
        super(outerContext, display);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_order);
        initView();
    }

    private void initView() {
        rylView = findViewById(R.id.ryl_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(layoutManager);
        mAdapter = new DispayOrderRyvAdapter(getContext());
        rylView.setAdapter(mAdapter);
        rylView.showEmpty();

        tvTotalPrice = findViewById(R.id.tv_total_price);
        tvPaidPrice = findViewById(R.id.tv_paid_price);
        tvComboPrice = findViewById(R.id.tv_combo_price);
        tvDiscountPrice = findViewById(R.id.tv_discount_price);
        tvNeedPrice = findViewById(R.id.tv_need_pay);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.d("DifferentOrderDislay onStart");
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.d("DifferentOrderDislay onStop");
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void orderEntity(OrderShowEvent event) {
        Logger.d("DifferentOrderDislay OrderShowEvent");
        mAdapter.clear();
        mAdapter.addAll(event.getOrderBaseList());

        tvTotalPrice.setText("¥ " + event.getTotalPrice());
        tvPaidPrice.setText("- ¥ " + event.getPaidPrice());
        tvComboPrice.setText("- ¥ " + event.getComboPrice());
        tvDiscountPrice.setText("- ¥ " + event.getDiscount());
        tvNeedPrice.setText("¥ " + event.getNeedPayPrice());
    }
}
