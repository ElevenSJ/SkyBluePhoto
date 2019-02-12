package com.sj.skyblue.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.skyblue.R;
import com.sj.skyblue.entity.GoodEntity;
import com.sj.skyblue.entity.ServiceEntity;
import com.sj.skyblue.entity.base.ResEntity;

/**
 * Created by Sunj on 2018/7/8.
 */

public class AddServiceRyvAdapter extends RecyclerArrayAdapter<ResEntity> {

    public AddServiceRyvAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddServiceRyvHolder(parent);
    }

    public static class AddServiceRyvHolder extends BaseViewHolder<ResEntity> {
        private LinearLayout mainLayout;
        private TextView tvName;
        private TextView tvPrice;

        public AddServiceRyvHolder(ViewGroup parent) {
            super(parent, R.layout.item_service);
            mainLayout = $(R.id.item_layout);
            tvName = $(R.id.tv_name);
            tvPrice = $(R.id.tv_price);
        }

        @Override
        public void setData(final ResEntity data) {
            super.setData(data);
            tvName.setText(data.getName());
            if (data.isSelected()) {
                tvName.setTextColor(mainLayout.getContext().getResources().getColor(R.color.white));
                tvPrice.setTextColor(mainLayout.getContext().getResources().getColor(R.color.white));
                mainLayout.setBackgroundResource(R.drawable.gradient_orange);
            } else {
                tvName.setTextColor(mainLayout.getContext().getResources().getColor(R.color.black));
                tvPrice.setTextColor(mainLayout.getContext().getResources().getColor(R.color.orange));
                mainLayout.setBackgroundResource(R.color.bg_gray);
            }
            tvPrice.setVisibility(data instanceof GoodEntity ? View.VISIBLE : View.GONE);
            if (data instanceof GoodEntity) {
                tvPrice.setText(String.format(getContext().getString(R.string.good_price), ((GoodEntity) data).getGoodPrice()));
            }
        }
    }
}
