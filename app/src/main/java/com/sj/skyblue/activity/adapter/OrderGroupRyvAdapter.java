package com.sj.skyblue.activity.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.module_lib.utils.CalculateUtils;
import com.sj.module_lib.widgets.AmountView;
import com.sj.skyblue.R;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.order.PhotoServiceItem;
import com.sj.skyblue.lisenter.AdapterItemDoLisenter;
import com.sj.skyblue.utils.DialogUtil;

/**
 * Created by Sunj on 2018/7/8.
 */

public class OrderGroupRyvAdapter extends RecyclerArrayAdapter<PhotoServiceItem> {
    private AdapterItemDoLisenter lisenter;
    private static Context mContext;

    public OrderGroupRyvAdapter(Context context, AdapterItemDoLisenter lisenter) {
        super(context);
        this.mContext = context;
        this.lisenter = lisenter;

    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderProductRyvHolder(parent, lisenter);
    }

    public static class OrderProductRyvHolder extends BaseViewHolder<PhotoServiceItem> {
        TextView tvName;
        AmountView tvPeopleCount;
        AmountView tvCount;
        TextView tvNum;
        TextView tvPeopleNum;
        TextView tvPrice;
        TextView tvTotalPrice;
        TextView tvStatus;
        TextView tvDo;
        AdapterItemDoLisenter lisenter;

        public OrderProductRyvHolder(ViewGroup parent, AdapterItemDoLisenter lisenter) {
            super(parent, R.layout.item_order_photo_group);
            tvName = $(R.id.tv_name);
            tvPeopleCount = $(R.id.tv_people_count);
            tvCount = $(R.id.tv_count);
            tvNum = $(R.id.tv_num);
            tvPeopleNum = $(R.id.tv_people_num);
            tvPrice = $(R.id.tv_price);
            tvTotalPrice = $(R.id.tv_total_price);
            tvStatus = $(R.id.tv_status);
            tvDo = $(R.id.tv_do);
            this.lisenter = lisenter;
        }

        @Override
        public void setData(final PhotoServiceItem data) {
            super.setData(data);
            tvName.setText(data.getBasePhotoItem().getServiceName());
            tvPeopleCount.setAmount(data.getBasePhotoItem().getPeopleCount());
            tvPeopleNum.setText(data.getBasePhotoItem().getPeopleCount() + "");
            tvCount.setAmount(data.getBasePhotoItem().getCount());
            tvNum.setText(data.getBasePhotoItem().getCount() + "");
            tvCount.setVisibility(getAdapterPosition() == 0 || !Constants.PAY_STATUS_NOT.equals(data.getBasePhotoItem().getStatus()) ? View.GONE : View.VISIBLE);
            tvNum.setVisibility(getAdapterPosition() == 0 || !Constants.PAY_STATUS_NOT.equals(data.getBasePhotoItem().getStatus()) ? View.VISIBLE : View.GONE);
            tvPeopleCount.setVisibility(getAdapterPosition() == 0 || !Constants.PAY_STATUS_NOT.equals(data.getBasePhotoItem().getStatus()) ? View.GONE : View.VISIBLE);
            tvPeopleNum.setVisibility(getAdapterPosition() == 0 || !Constants.PAY_STATUS_NOT.equals(data.getBasePhotoItem().getStatus()) ? View.VISIBLE : View.GONE);
            tvCount.setEditable(Constants.PAY_STATUS_NOT.equals(data.getBasePhotoItem().getStatus()));
            tvPeopleCount.setEditable(Constants.PAY_STATUS_NOT.equals(data.getBasePhotoItem().getStatus()));
            tvCount.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, int amount) {
                    data.getBasePhotoItem().setCount(amount);
                    tvTotalPrice.setText("¥ " + CalculateUtils.mul(data.getBasePhotoItem().getPrice(), amount + ""));
                    if (lisenter != null) {
                        lisenter.doItem(AdapterItemDoLisenter.TYPE_UPDATE, getAdapterPosition());
                    }
                }
            });
            tvPeopleCount.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, int amount) {
                    data.getBasePhotoItem().setPeopleCount(amount);
//                    tvTotalPrice.setText("¥ " + CalculateUtils.mul(data.getBasePhotoItem().getPrice(), amount + ""));
//                    if (lisenter != null) {
//                        lisenter.doItem(AdapterItemDoLisenter.TYPE_UPDATE, getAdapterPosition());
//                    }
                }
            });
            tvPrice.setText("¥ " + data.getBasePhotoItem().getPrice());
            tvTotalPrice.setText("¥ " + CalculateUtils.mul(data.getBasePhotoItem().getPrice(), data.getBasePhotoItem().getCount() + ""));
            tvStatus.setText(Constants.orderPayStatus.get(data.getBasePhotoItem().getStatus()));
            if (Constants.PAY_STATUS_PAID.equals(data.getBasePhotoItem().getStatus())) {
                tvDo.setText("退款");
                tvDo.setVisibility(View.VISIBLE);
            } else if (Constants.PAY_STATUS_NOT.equals(data.getBasePhotoItem().getStatus())) {
                tvDo.setText(getAdapterPosition() == 0 ? "更改/删除" : "删除");
                tvDo.setVisibility(View.VISIBLE);
            } else if ((data.getBasePhotoItem().getCount() - data.getBasePhotoItem().getRefundCount()) > 0) {
                tvDo.setText("退款");
                tvDo.setVisibility(View.VISIBLE);
            } else {
                tvDo.setVisibility(View.GONE);
            }
            tvDo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (("退款").equals(tvDo.getText().toString())) {
                        if (lisenter != null) {
                            lisenter.doItem(AdapterItemDoLisenter.TYPE_PAYBACK, getAdapterPosition());
                        }
                    } else {
                        if (lisenter != null) {
                            if (getAdapterPosition() == 0) {
                                DialogUtil.getInstance().showChooseDialog(OrderGroupRyvAdapter.mContext, new String[]{"更改", "删除"}, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (which==0){
                                            lisenter.doItem(AdapterItemDoLisenter.TYPE_CHANGE, getAdapterPosition());
                                        }else{
                                            lisenter.doItem(AdapterItemDoLisenter.TYPE_DELETE, getAdapterPosition());
                                        }
                                    }
                                });

                            } else {
                                lisenter.doItem(AdapterItemDoLisenter.TYPE_DELETE, getAdapterPosition());
                            }
                        }
                    }
                }
            });
        }
    }
}
