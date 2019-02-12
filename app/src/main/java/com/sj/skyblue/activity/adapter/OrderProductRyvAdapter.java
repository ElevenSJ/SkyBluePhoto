package com.sj.skyblue.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.CalculateUtils;
import com.sj.module_lib.utils.StringUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.module_lib.widgets.AmountView;
import com.sj.skyblue.R;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.order.OrderProduct;
import com.sj.skyblue.lisenter.AdapterItemDoLisenter;

/**
 * Created by Sunj on 2018/7/8.
 */

public class OrderProductRyvAdapter extends RecyclerArrayAdapter<OrderProduct> {
    public AdapterItemDoLisenter lisenter;

    public OrderProductRyvAdapter(Context context) {
        super(context);

    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderProductRyvHolder(parent);
    }

    public void setItemDoLisenter(AdapterItemDoLisenter lisenter) {
        this.lisenter = lisenter;
    }

    public static class OrderProductRyvHolder extends BaseViewHolder<OrderProduct> {
        TextView tvName;
        AmountView tvCount;
        TextView tvPrice;
        TextView tvTotalPrice;
        TextView tvStatus;
        TextView tvDo;

        public OrderProductRyvHolder(ViewGroup parent) {
            super(parent, R.layout.item_order_product);
            tvName = $(R.id.tv_name);
            tvCount = $(R.id.tv_count);
            tvPrice = $(R.id.tv_price);
            tvTotalPrice = $(R.id.tv_total_price);
            tvStatus = $(R.id.tv_status);
            tvDo = $(R.id.tv_do);
        }

        public AdapterItemDoLisenter getLisenter() {
            return ((OrderProductRyvAdapter) getOwnerAdapter()).lisenter;
        }

        @Override
        public void setData(final OrderProduct data) {
            super.setData(data);
            tvName.setText(data.getServiceName());
            tvCount.setAmount(data.getCount());
            tvCount.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, int amount) {
                    data.setCount(amount);
                    tvTotalPrice.setText("¥ " + CalculateUtils.mul(data.getPrice(), amount + ""));
                    if (getLisenter() != null) {
                        getLisenter().doItem(AdapterItemDoLisenter.TYPE_UPDATE, getDataPosition());
                    }
                }
            });
            tvCount.setEditable(Constants.PAY_STATUS_NOT.equals(data.getStatus()));
            tvPrice.setText("¥ " + data.getPrice());
            tvTotalPrice.setText("¥ " + CalculateUtils.mul(data.getPrice(), data.getCount() + ""));
            tvStatus.setText(Constants.orderPayStatus.get(data.getStatus()));
            if (Constants.PAY_STATUS_PAID.equals(data.getStatus())) {
                tvDo.setText("退款");
                tvDo.setVisibility(View.VISIBLE);
            } else if (Constants.PAY_STATUS_NOT.equals(data.getStatus())) {
                tvDo.setText("删除");
                tvDo.setVisibility(View.VISIBLE);
            } else if ((data.getCount() - data.getRefundCount()) > 0) {
                tvDo.setText("退款");
                tvDo.setVisibility(View.VISIBLE);
            } else {
                tvDo.setVisibility(View.GONE);
            }
            tvDo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Constants.PAY_STATUS_PAID.equals(data.getStatus())) {
                        if (getLisenter() != null) {
                            getLisenter().doItem(AdapterItemDoLisenter.TYPE_PAYBACK, getDataPosition());
                        }
                    } else {
                        if (getLisenter() != null) {
                            getLisenter().doItem(AdapterItemDoLisenter.TYPE_DELETE, getDataPosition());
                        }
                    }
                }
            });
        }
    }
}
