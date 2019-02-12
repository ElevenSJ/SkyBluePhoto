package com.sj.skyblue.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.module_lib.utils.CalculateUtils;
import com.sj.module_lib.utils.StringUtils;
import com.sj.module_lib.widgets.AmountView;
import com.sj.skyblue.R;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.OrderItem;
import com.sj.skyblue.entity.order.OrderPhoto;
import com.sj.skyblue.entity.order.OrderProduct;
import com.sj.skyblue.entity.order.base.OrderBase;
import com.sj.skyblue.event.OrderCreateEvent;
import com.sj.skyblue.lisenter.AdapterItemDoLisenter;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Sunj on 2018/7/8.
 */

public class DispayOrderRyvAdapter extends RecyclerArrayAdapter<OrderBase> {

    public DispayOrderRyvAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new DispayOrderRyvHolder(parent);
    }

    public static class DispayOrderRyvHolder extends BaseViewHolder<OrderBase> {
        LinearLayout mainLayout;
        TextView tvName;
        TextView tvNum;
        TextView tvPrice;
        TextView tvTotalPrice;


        public DispayOrderRyvHolder(ViewGroup parent) {
            super(parent, R.layout.item_display_order);
            mainLayout = $(R.id.item_layout);
            tvName = $(R.id.tv_order_name);
            tvNum = $(R.id.tv_order_num);
            tvPrice = $(R.id.tv_order_pirce);
            tvTotalPrice = $(R.id.tv_order_total_price);
        }

        @Override
        public void setData(final OrderBase data) {
            super.setData(data);
            mainLayout.setBackgroundColor(getAdapterPosition() % 2 == 0 ? mainLayout.getContext().getResources().getColor(R.color.bg_gray) : mainLayout.getContext().getResources().getColor(R.color.divider_color));
            if (data instanceof OrderPhoto) {
                StringBuilder nameBuilder = new StringBuilder();
                nameBuilder.append(((OrderPhoto) data).getServiceName());
//                if (!StringUtils.isEmpty(((OrderPhoto) data).getSize())||!StringUtils.isEmpty(((OrderPhoto) data).getColor()))
//                {
//                    nameBuilder.append("(");
//                    nameBuilder.append(StringUtils.isEmpty(((OrderPhoto) data).getSize())?"":((OrderPhoto) data).getSize());
//                    nameBuilder.append(StringUtils.isEmpty(((OrderPhoto) data).getColor())?"":((OrderPhoto) data).getColor());
//                    nameBuilder.append(")");
//                }
                tvName.setText(nameBuilder.toString());
                tvNum.setText("× "+((OrderPhoto) data).getCount()+"");
                tvPrice.setText("¥ "+((OrderPhoto) data).getPrice());
                tvTotalPrice.setText("¥ "+ CalculateUtils.mul(((OrderPhoto) data).getPrice(),((OrderPhoto) data).getCount()+""));
            }else if (data instanceof OrderProduct) {
                tvName.setText(((OrderProduct) data).getServiceName());
                tvNum.setText(((OrderProduct) data).getCount()+"");
                tvPrice.setText("¥ " + ((OrderProduct) data).getPrice());
                tvTotalPrice.setText("¥ " + CalculateUtils.mul(((OrderProduct) data).getPrice(), ((OrderProduct) data).getCount() + ""));
            }
        }
    }
}
