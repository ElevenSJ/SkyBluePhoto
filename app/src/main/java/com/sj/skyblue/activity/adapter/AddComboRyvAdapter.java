package com.sj.skyblue.activity.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.skyblue.R;
import com.sj.skyblue.entity.OrderCombo;

/**
 * Created by Sunj on 2018/7/8.
 */

public class AddComboRyvAdapter extends RecyclerArrayAdapter<OrderCombo> {

    public AddComboRyvAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddComboRyvHolder(parent);
    }

    public static class AddComboRyvHolder extends BaseViewHolder<OrderCombo> {
        private LinearLayout mainLayout;
        private TextView tvName;
        private TextView tvPrice;

        public AddComboRyvHolder(ViewGroup parent) {
            super(parent, R.layout.item_combo);
            mainLayout = $(R.id.item_layout);
            tvName = $(R.id.tv_name);
            tvPrice = $(R.id.tv_price);
        }

        @Override
        public void setData(final OrderCombo data) {
            super.setData(data);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(data.getName());
            stringBuilder.append("\n");
            stringBuilder.append("( ");
            if (null!=data.getServiceCombo()) {
                for (int i = 0; i < data.getServiceCombo().size(); i++) {
                    stringBuilder.append(data.getServiceCombo().get(i).getName());
                    if (i<data.getServiceCombo().size()-1) {
                        stringBuilder.append(" + ");
                    }
                }
            }
            stringBuilder.append(" )");
            tvName.setText(stringBuilder.toString());
            tvPrice.setText("优惠价格：¥"+data.getPrice());
            if (data.isSelected()) {
                tvName.setTextColor(mainLayout.getContext().getResources().getColor(R.color.white));
                tvPrice.setTextColor(mainLayout.getContext().getResources().getColor(R.color.white));
                mainLayout.setBackgroundResource(R.drawable.gradient_orange);
            } else {
                tvName.setTextColor(mainLayout.getContext().getResources().getColor(R.color.black));
                tvPrice.setTextColor(mainLayout.getContext().getResources().getColor(R.color.orange));
                mainLayout.setBackgroundResource(R.color.bg_gray);
            }
        }
    }
}
