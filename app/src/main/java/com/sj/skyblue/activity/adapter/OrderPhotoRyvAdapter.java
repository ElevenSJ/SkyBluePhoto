package com.sj.skyblue.activity.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.module_lib.utils.CalculateUtils;
import com.sj.skyblue.R;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.order.OrderPhoto;
import com.sj.skyblue.entity.order.OrderService;
import com.sj.skyblue.lisenter.AdapterItemDoLisenter;
import com.sj.skyblue.utils.DialogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunj on 2018/7/8.
 */

public class OrderPhotoRyvAdapter extends RecyclerArrayAdapter<OrderPhoto> {
    public AdapterItemDoLisenter lisenter;

    public OrderPhotoRyvAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderPhotoRyvHolder(parent);
    }

    public void setItemDoLisenter(AdapterItemDoLisenter lisenter) {
        this.lisenter = lisenter;
    }


    public static class OrderPhotoRyvHolder extends BaseViewHolder<OrderPhoto> {
        TextView tvSize;
        TextView tvColor;
        TextView tvPrice;
        TextView tvStatus;
        TextView tvDo;


        public OrderPhotoRyvHolder(ViewGroup parent) {
            super(parent, R.layout.item_order_photo_add);
            tvSize = $(R.id.tv_size);
            tvColor = $(R.id.tv_color);
            tvPrice = $(R.id.tv_price);
            tvStatus = $(R.id.tv_status);
            tvDo = $(R.id.tv_do);
        }

        public AdapterItemDoLisenter getLisenter() {
            return ((OrderPhotoRyvAdapter) getOwnerAdapter()).lisenter;
        }

        @Override
        public void setData(final OrderPhoto data) {
            super.setData(data);
            if (data.getSizes() != null && data.getSizes().size() > 0) {
                Drawable dwRight = ContextCompat.getDrawable(tvSize.getContext(), R.drawable.img_arrow_down);
                dwRight.setBounds(0, 0, dwRight.getMinimumWidth(), dwRight.getMinimumHeight());
                tvSize.setCompoundDrawables(null, null, dwRight, null);
                tvSize.setBackgroundResource(R.drawable.gray_white_bg);
                //选择照片尺寸
                tvSize.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUtil.getInstance().showChooseDialog(tvSize.getContext(), data.getSizes().toArray(new String[data.getSizes().size()]), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tvSize.setText(data.getSizes().get(which));
                                data.setSize(data.getSizes().get(which));
                            }
                        });
                    }
                });
                tvSize.setText(data.getSizes().get(0));
                data.setSize(data.getSizes().get(0));
            } else {
                tvSize.setText(data.getSize());
                tvSize.setOnClickListener(null);
                tvSize.setBackgroundResource(R.color.transparent);
                tvSize.setCompoundDrawables(null, null, null, null);
            }
            tvColor.setText(data.getColor());
            tvColor.setBackgroundResource(R.color.transparent);
            tvPrice.setText("¥ " + data.getPrice());
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
                    if (("退款").equals(tvDo.getText().toString())) {
                        if (getLisenter() != null) {
                            getLisenter().doItem(AdapterItemDoLisenter.TYPE_PAYBACK, getAdapterPosition());
                        }
                    } else {
                        if (getLisenter() != null) {
                            getLisenter().doItem(AdapterItemDoLisenter.TYPE_DELETE, getAdapterPosition());
                        }
                    }
                }
            });
        }
    }
}
