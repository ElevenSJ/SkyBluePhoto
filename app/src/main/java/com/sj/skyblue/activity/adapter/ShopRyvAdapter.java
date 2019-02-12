package com.sj.skyblue.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.module_lib.glide.ImageUtils;
import com.sj.module_lib.utils.StringUtils;
import com.sj.skyblue.R;
import com.sj.skyblue.entity.GoodEntity;

/**
 * Created by Sunj on 2018/7/8.
 */

public class ShopRyvAdapter extends RecyclerArrayAdapter<GoodEntity> {

    public ShopRyvAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopRyvHolder(parent);
    }

    public static class ShopRyvHolder extends BaseViewHolder<GoodEntity> {
        private LinearLayout mainLayout;
        private ImageView imgPic;
        private TextView tvName;
        private TextView tvRemark;
        private TextView tvPrice;
        private TextView tvStock;


        public ShopRyvHolder(ViewGroup parent) {
            super(parent, R.layout.item_good);
            mainLayout = $(R.id.item_layout);
            imgPic = $(R.id.img_good);
            tvName= $(R.id.tv_name);
            tvRemark= $(R.id.tv_desc);
            tvPrice= $(R.id.tv_price);
            tvStock= $(R.id.tv_stock);
        }

        @Override
        public void setData(final GoodEntity data) {
            super.setData(data);
            ImageUtils.loadImageView(data.getGoodPicture(),imgPic);
            tvName.setText(data.getName());
            tvRemark.setVisibility(View.GONE);
            tvPrice.setText(String.format(getContext().getString(R.string.good_price),data.getGoodPrice()));
            tvStock.setText(String.format(getContext().getString(R.string.good_stock),data.getGoodNum()));
            if (data.isSelected()){
                mainLayout.setBackgroundResource(R.drawable.gradient_orange);
            }else{
                mainLayout.setBackgroundResource(R.color.white);
            }
        }
    }
}
