package com.sj.skyblue.activity.fragment.business.order;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.List;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.http.BaseResponse;
import com.sj.module_lib.http.CommonCallback;
import com.sj.module_lib.http.ServerResultBack;
import com.sj.module_lib.utils.DateUtils;
import com.sj.module_lib.utils.StringUtils;
import com.sj.skyblue.R;
import com.sj.skyblue.activity.adapter.AppointmentRyvAdapter;
import com.sj.skyblue.activity.base.BaseFragment;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.AppointmentEntity;
import com.sj.skyblue.entity.GoodServiceEntity;
import com.sj.skyblue.entity.OrderData;
import com.sj.skyblue.entity.OrderItem;
import com.sj.skyblue.entity.ServiceEntity;
import com.sj.skyblue.entity.base.BaseDataList;
import com.sj.skyblue.event.OrderCreateEvent;
import com.sj.skyblue.event.PresentationChangeEvent;
import com.sj.skyblue.http.API;

import com.alibaba.fastjson.TypeReference;
import com.sj.skyblue.manager.handler.OrderDetailHandler;
import com.sj.skyblue.manager.handler.OrderItemHandler;
import com.sj.skyblue.utils.DialogUtil;
import com.sj.skyblue.view.OrderCreateDialogView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 预约记录列表页面
 * Created by Sunj on 2018/11/4.
 */

public class OrderListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OrderCreateDialogView.IServiceItemSelectLisenter {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;
    @BindView(R.id.tv_busy_num)
    TextView tvBusyNum;
    AppointmentRyvAdapter mAdapter;

    boolean isRefresh = false;
    String phoneNum;
    boolean isAddOrder = false;

    private static final String SAVE_STATE_POSITION = "save_state_position";
    private static final String SAVE_STATE_PAGENUM = "save_state_pagenum";
    private static final String SAVE_STATE_PHONE = "save_state_phone";
    private String mCurrentId;


    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            onRefresh();
        }
    };

    public static OrderListFragment newInstance() {
        Bundle args = new Bundle();
        OrderListFragment fragment = new OrderListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_appointment_list;
    }

    @OnClick({R.id.tv_search, R.id.tv_add_order, R.id.tv_order_time, R.id.tv_order_service, R.id.tv_order_status})
    public void onViewClicked(View view) {
        hideSoftInput();
        switch (view.getId()) {
            case R.id.tv_search:
                onRefresh();
                break;
            case R.id.tv_add_order:
                showCreateOrderDialog(new OrderCreateEvent(DateUtils.getCurrentTime()));
                break;
            case R.id.tv_order_time:
                break;
            case R.id.tv_order_service:
                break;
            case R.id.tv_order_status:
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_STATE_POSITION, mCurrentId);
        outState.putString(SAVE_STATE_PHONE, phoneNum);
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        EventBus.getDefault().register(this);
        handler.removeMessages(100);
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                phoneNum = s.toString();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(layoutManager);
        rylView.setRefreshListener(this);
        mAdapter = new AppointmentRyvAdapter(_mActivity);
//        mAdapter.sort();
        rylView.setAdapter(mAdapter);
        rylView.showEmpty();

        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                hideSoftInput();
                showContent(mAdapter.getItem(position).getOrderId());
            }
        });
        if (savedInstanceState != null) {
            mCurrentId = savedInstanceState.getString(SAVE_STATE_POSITION);
            phoneNum = savedInstanceState.getString(SAVE_STATE_PHONE);
            showContent(mCurrentId);
        }
        post(new Runnable() {
            @Override
            public void run() {
                onRefresh();
            }
        });
    }

    private void getBusyNum() {
        API.queryDressCount(new ServerResultBack() {
            @Override
            public Type getType() {
                return new TypeReference<BaseResponse<String>>() {
                }.getType();
            }

            @Override
            public void onSuccess(Object data) {
                tvBusyNum.setText("当前忙碌人数：" + data + "人");
            }
        });
    }

    private void showContent(String id) {
        Logger.d("showContent : id = " + id);
        if (StringUtils.isEmpty(id) ) {
            return;
        }
        if (id.equals(mCurrentId) ) {
            for (int i = 0; i < mAdapter.getAllData().size(); i++) {
                OrderItem orderItem = mAdapter.getItem(i);
                if (!StringUtils.isEmpty(orderItem.getOrderId())) {
                    if (orderItem.getOrderId().equals(mCurrentId)&&!orderItem.isSelected()){
                        orderItem.setSelected(true);
                        mAdapter.notifyItemChanged(i);
                        break;
                    }
                }
            }
            return;
        }
        mCurrentId = id;
        for (OrderItem orderItem : mAdapter.getAllData()) {
            if (!StringUtils.isEmpty(orderItem.getOrderId())) {
                orderItem.setSelected(orderItem.getOrderId().equals(id));
            }
        }
        mAdapter.notifyDataSetChanged();
        OrderContentFragment fragment = OrderContentFragment.newInstance(id);
        ((OrderMainFragment) getParentFragment()).switchContentFragment(fragment);
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        getData();
        getBusyNum();
    }

    void getData() {
        handler.removeMessages(100);
        API.queryOrderList(new ServerResultBack() {
            @Override
            public void onSuccess(Object data) {
                if (isRefresh) {
                    mAdapter.clear();
                }
                List<OrderData> result = (List<OrderData>) data;
                OrderItemHandler.getInstance().handler(result);
                mAdapter.addAll(OrderItemHandler.getInstance().getOrderItems());
                showContent(mCurrentId);
                handler.sendEmptyMessageDelayed(100,5000);
            }

            @Override
            public Type getType() {
                return new TypeReference<BaseResponse<List<OrderData>>>() {
                }.getType();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                promptDialog.dismiss();
            }
        });
    }

    @Subscribe
    public void showCreateOrderDialog(final OrderCreateEvent orderCreateEvent) {
        promptDialog.showLoading("正在加载");
        API.queryServiceList(new ServerResultBack() {
            @Override
            public void onSuccess(Object data) {
                final GoodServiceEntity goodServiceEntity = (GoodServiceEntity) data;
                DialogUtil.getInstance().showBigViewDialog(_mActivity, R.layout.dialog_create_order, new DialogUtil.ViewInterface() {
                    @Override
                    public void getChildView(AlertDialog dialog, View view, int layoutResId) {
                        OrderCreateDialogView orderCreateDialogView = new OrderCreateDialogView(_mActivity, dialog, view);
                        orderCreateDialogView.initModule(orderCreateEvent.time, goodServiceEntity.getServices());
                        orderCreateDialogView.setIServiceItemSelectLisenter(OrderListFragment.this);
                    }
                });
            }

            @Override
            public Type getType() {
                return new TypeReference<BaseResponse<GoodServiceEntity>>() {
                }.getType();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                promptDialog.dismiss();
            }
        });
    }

    @Override
    public void callback(String time, String phoneNum, ServiceEntity resEntity) {
        promptDialog.showLoading("正在新建订单");
        API.appSaveOrder(time, phoneNum, resEntity.getId(), new CommonCallback() {

            @Override
            public void onSuccess(String message) {
                isAddOrder = true;
                promptDialog.showSuccessDelay(message, 200);
                onRefresh();

            }

            @Override
            public void onFinish() {
                super.onFinish();
                promptDialog.dismiss();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeMessages(100);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        handler.removeMessages(100);
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        handler.sendEmptyMessageDelayed(100,1000);
    }
}
