package com.sj.skyblue.activity.adapter;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.module_lib.utils.StringUtils;
import com.sj.skyblue.R;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.AppointmentEntity;
import com.sj.skyblue.entity.OrderItem;
import com.sj.skyblue.event.OrderCreateEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Sunj on 2018/7/8.
 */

public class AppointmentRyvAdapter extends RecyclerArrayAdapter<OrderItem> {

    public AppointmentRyvAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new AppointmentRyvHolder(parent);
    }

    public static class AppointmentRyvHolder extends BaseViewHolder<OrderItem> {
        LinearLayout mainLayout;
        TextView tvTime;
        TextView tvService;
        TextView tvPayTime;
        TextView tvStatus;
        TextView tvDo;


        public AppointmentRyvHolder(ViewGroup parent) {
            super(parent, R.layout.item_appointment);
            mainLayout = $(R.id.item_layout);
            tvTime = $(R.id.tv_order_time);
            tvService = $(R.id.tv_order_service);
            tvPayTime = $(R.id.tv_order_pay_time);
            tvStatus = $(R.id.tv_order_status);
            tvDo = $(R.id.tv_order_do);
        }

        @Override
        public void setData(final OrderItem data) {
            super.setData(data);
            mainLayout.setBackgroundColor(getAdapterPosition() % 2 == 0 ? mainLayout.getContext().getResources().getColor(R.color.bg_gray) : mainLayout.getContext().getResources().getColor(R.color.divider_color));
            tvTime.setText(data.getTime());
            tvService.setText(data.getServiceName());
            tvPayTime.setText(data.getPayTime());
            tvStatus.setText(Constants.orderStatus.get(data.getAppStatus()));
            if (Constants.orderStatusBg.containsKey(data.getAppStatus())){
                tvStatus.setBackground(mainLayout.getContext().getResources().getDrawable(Constants.orderStatusBg.get(data.getAppStatus())));
            }else{
                tvStatus.setBackground(null);
            }

            tvDo.setVisibility(StringUtils.isEmpty(data.getOrderId())? View.VISIBLE:View.GONE);
            tvDo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new OrderCreateEvent(data.getTime()));
                }
            });
            if (data.isSelected()) {
                tvTime.setTextColor(mainLayout.getContext().getResources().getColor(R.color.white));
                tvService.setTextColor(mainLayout.getContext().getResources().getColor(R.color.white));
//                tvStatus.setTextColor(mainLayout.getContext().getResources().getColor(R.color.white));
                tvPayTime.setTextColor(mainLayout.getContext().getResources().getColor(R.color.white));
                mainLayout.setBackgroundResource(R.drawable.gradient_orange);
            } else {
                tvTime.setTextColor(mainLayout.getContext().getResources().getColor(R.color.black_alpha80));
                tvService.setTextColor(mainLayout.getContext().getResources().getColor(R.color.black_alpha80));
//                tvStatus.setTextColor(mainLayout.getContext().getResources().getColor(R.color.black_alpha80));
                tvPayTime.setTextColor(mainLayout.getContext().getResources().getColor(R.color.black_alpha80));
                mainLayout.setBackgroundColor(getAdapterPosition() % 2 == 0 ? mainLayout.getContext().getResources().getColor(R.color.bg_gray) : mainLayout.getContext().getResources().getColor(R.color.divider_color));
            }
        }
    }
}
