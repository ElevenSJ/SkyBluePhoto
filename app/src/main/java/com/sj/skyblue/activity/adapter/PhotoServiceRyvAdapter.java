package com.sj.skyblue.activity.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.CalculateUtils;
import com.sj.module_lib.utils.StringUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.skyblue.R;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.order.OrderPhoto;
import com.sj.skyblue.entity.order.OrderService;
import com.sj.skyblue.entity.order.PhotoServiceItem;
import com.sj.skyblue.event.OrderAddExtEvent;
import com.sj.skyblue.event.OrderDeleteEvent;
import com.sj.skyblue.event.OrderPayRefundEvent;
import com.sj.skyblue.event.OrderServicePriceEvent;
import com.sj.skyblue.lisenter.AdapterItemAddLisenter;
import com.sj.skyblue.lisenter.AdapterItemDoLisenter;
import com.sj.skyblue.utils.DialogUtil;
import com.sj.skyblue.utils.PromptDialogUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;

/**
 * Created by Sunj on 2018/7/8.
 */

public class PhotoServiceRyvAdapter extends RecyclerArrayAdapter<PhotoServiceItem> {
    public static Context mContext;
    public AdapterItemAddLisenter addLisenter;
    public AdapterItemDoLisenter lisenter;

    public PhotoServiceRyvAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoServiceRyvHolder(parent);
    }

    public void setItemAddLisenter(AdapterItemAddLisenter addLisenter) {
        this.addLisenter = addLisenter;
    }

    public void setItemDoLisenter(AdapterItemDoLisenter lisenter) {
        this.lisenter = lisenter;
    }

    public static class PhotoServiceRyvHolder extends BaseViewHolder<PhotoServiceItem> {
        TextView tvServiceName;
        ImageView imgPic;
        TextView tvSize;
        TextView tvColor;
        TextView tvPrice;
        TextView tvStatus;
        TextView tvDo;
        TextView tvBasePhotoPrice;
        TextView btAdd1;
        TextView btAdd2;
        TextView btAdd3;
        LinearLayout layoutAdd;
        EasyRecyclerView rylView;
        TextView tvAddPrintTotalPrice;
        LinearLayout layoutAddPrint;
        ConstraintLayout mainLayout;
        OrderPhotoRyvAdapter photoRyvAdapter;
        TextView tvExt;

        public AdapterItemAddLisenter getItemAddLisenter() {
            return ((PhotoServiceRyvAdapter) getOwnerAdapter()).addLisenter;
        }

        public AdapterItemDoLisenter getItemDoLisenter() {
            return ((PhotoServiceRyvAdapter) getOwnerAdapter()).lisenter;
        }

        public PhotoServiceRyvHolder(ViewGroup parent) {
            super(parent, R.layout.item_photo_add_service);
            mainLayout = $(R.id.item_layout);
            tvServiceName = $(R.id.tv_service_name);
            imgPic = $(R.id.img_pic);
            tvSize = $(R.id.tv_size);
            tvColor = $(R.id.tv_color);
            tvPrice = $(R.id.tv_price);
            tvStatus = $(R.id.tv_status);
            tvDo = $(R.id.tv_do);
            tvBasePhotoPrice = $(R.id.tv_base_photo_price);
            btAdd1 = $(R.id.bt_add1);
            btAdd2 = $(R.id.bt_add2);
            btAdd3 = $(R.id.bt_add3);
            layoutAdd = $(R.id.layout_add);
            rylView = $(R.id.ryl_view);
            tvAddPrintTotalPrice = $(R.id.tv_add_print_total_price);
            layoutAddPrint = $(R.id.layout_add_print);
            tvExt = $(R.id.bt_ext);

            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            DividerDecoration dividerDecoration = new DividerDecoration(mContext.getResources().getColor(R.color.divider_color), 1, 0, 0);
            dividerDecoration.setDrawLastItem(false);
            rylView.addItemDecoration(dividerDecoration);
            rylView.setLayoutManager(layoutManager);
            photoRyvAdapter = new OrderPhotoRyvAdapter(mContext);
            rylView.setAdapter(photoRyvAdapter);
        }

        @Override
        public void setData(final PhotoServiceItem data) {
            super.setData(data);
            tvExt.setVisibility(data.getIsExtra().equals("0") ? View.VISIBLE : View.GONE);
            tvExt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PhotoServiceItem photoServiceItem = new PhotoServiceItem();
                    photoServiceItem.setPrintPrizes(data.getPrintPrizes());
                    photoServiceItem.setSizes(data.getSizes());
                    photoServiceItem.setColors(data.getColors());
                    photoServiceItem.setServiceName("加选");
                    photoServiceItem.setBaseOrderId(data.getBaseOrderId());
                    photoServiceItem.setIsExtra("1");

                    OrderPhoto baseOrderPhoto = new OrderPhoto();
                    baseOrderPhoto.setServiceName(photoServiceItem.getServiceName());
                    baseOrderPhoto.setSize(data.getBasePhotoItem().getSize());
                    baseOrderPhoto.setColor(data.getBasePhotoItem().getColor());
                    baseOrderPhoto.setPrice(data.getTierAddPrice());
                    baseOrderPhoto.setId("empty_" + System.currentTimeMillis());
                    baseOrderPhoto.setCount(1);
                    baseOrderPhoto.setPeopleCount(1);
                    photoServiceItem.setBasePhotoItem(baseOrderPhoto);
                    if (getItemAddLisenter() != null) {
                        getItemAddLisenter().addItem(photoServiceItem);
                    }
                }
            });
            //显示标题图片
            tvServiceName.setText(data.getServiceName());
            tvServiceName.setSelected(data.getIsExtra().equals("0"));
//            ImageUtils.loadImageWithError(data.getPicUrl(), R.drawable.img_staff_man, imgPic);
            //显示基础照片信息
            tvSize.setText(data.getBasePhotoItem().getSize());
            tvColor.setText(data.getBasePhotoItem().getColor());
            tvPrice.setText("¥ " + data.getBasePhotoItem().getPrice());
            tvStatus.setText(Constants.orderPayStatus.get(data.getBasePhotoItem().getStatus()));
            if (Constants.PAY_STATUS_PAID.equals(data.getBasePhotoItem().getStatus())) {
                tvDo.setText("退款");
                tvDo.setVisibility(View.VISIBLE);
            } else if (Constants.PAY_STATUS_NOT.equals(data.getBasePhotoItem().getStatus())) {
                if (data.getIsExtra().equals("0")) {
                    tvDo.setText("更改/删除");
                } else {
                    tvDo.setText("删除");
                }
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
                        if (getItemDoLisenter() != null) {
                            getItemDoLisenter().doItem(AdapterItemDoLisenter.TYPE_PAYBACK, getAdapterPosition());
                        }
                    } else {
                        if (data.getIsExtra().equals("0")) {
                            if (getItemDoLisenter() != null) {
                                DialogUtil.getInstance().showChooseDialog(PhotoServiceRyvAdapter.mContext, new String[]{"更改", "删除"}, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (which==0){
                                            getItemDoLisenter().doItem(AdapterItemDoLisenter.TYPE_CHANGE, getAdapterPosition());
                                        }else{
                                            getItemDoLisenter().doItem(AdapterItemDoLisenter.TYPE_DELETE, getAdapterPosition());
                                        }
                                    }
                                });
                            }
                        } else {
                            if (getItemDoLisenter() != null) {
                                getItemDoLisenter().doItem(AdapterItemDoLisenter.TYPE_DELETE, getAdapterPosition());
                            }
                        }

                    }
                }
            });

            if (!data.getBasePhotoItem().getStatus().equals(Constants.PAY_STATUS_NOT)) {
                tvColor.setBackgroundResource(R.color.transparent);
                tvColor.setCompoundDrawables(null, null, null, null);
                tvSize.setBackgroundResource(R.color.transparent);
                tvSize.setCompoundDrawables(null, null, null, null);
                tvColor.setOnClickListener(null);
                tvSize.setOnClickListener(null);
            } else {
                //选择照片尺寸
                tvSize.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<String> sizes = new ArrayList<>();
                        for (OrderService.SizeColor sizeColor : data.getSizes()) {
                            sizes.add(sizeColor.getName());
                        }
                        DialogUtil.getInstance().showChooseDialog(mContext, sizes.toArray(new String[sizes.size()]), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tvSize.setText(data.getSizes().get(which).getName());
                                data.getBasePhotoItem().setSize(data.getSizes().get(which).getName());
                            }
                        });
                    }
                });
                Drawable dwRight = ContextCompat.getDrawable(mContext, R.drawable.img_arrow_down);
                dwRight.setBounds(0, 0, dwRight.getMinimumWidth(), dwRight.getMinimumHeight());
                tvSize.setCompoundDrawables(null, null, dwRight, null);
                tvSize.setBackgroundResource(R.drawable.gray_white_bg);

                //加选照片不可选底色
                if (!data.getIsExtra().equals("0")) {
                    tvColor.setBackgroundResource(R.color.transparent);
                    tvColor.setCompoundDrawables(null, null, null, null);
                    tvColor.setOnClickListener(null);
                } else {
                    tvColor.setCompoundDrawables(null, null, dwRight, null);
                    tvColor.setBackgroundResource(R.drawable.gray_white_bg);
                    //选择照片底色
                    tvColor.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            List<String> colors = new ArrayList<>();
                            for (OrderService.SizeColor sizeColor : data.getColors()) {
                                colors.add(sizeColor.getName());
                            }
                            DialogUtil.getInstance().showChooseDialog(mContext, colors.toArray(new String[colors.size()]), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String color = data.getColors().get(which).getName();
                                    data.getBasePhotoItem().setColor(color);
                                    tvColor.setText(color);
                                    //更新所有底色
                                    if (getItemAddLisenter() != null) {
                                        getItemAddLisenter().updateColor(color);
                                    }
                                }
                            });
                        }
                    });
                }
            }
            try {
                tvBasePhotoPrice.setText("小计：¥ " + (CalculateUtils.mul(data.getBasePhotoItem().getPrice(),  "1")) + (data.getPrePrice().equals("0") ? "" : "(优惠 ¥"+CalculateUtils.mul(data.getPrePrice(), "1")+")"));
                OrderServicePriceEvent orderServicePriceEvent = new OrderServicePriceEvent.Builder()
                        .id(data.getBasePhotoItem().getOrderId())
                        .price(data.getBasePhotoItem().getPrice())
                        .status(data.getBasePhotoItem().getStatus())
                        .count(data.getBasePhotoItem().getCount() + "").build();
                EventBus.getDefault().post(orderServicePriceEvent);
            } catch (Exception e) {
                e.printStackTrace();
                Logger.e("基础照片服务价格格式化异常");
            }
            if (data.getPrintPrizes() != null) {
                for (int i = 0; i < data.getPrintPrizes().size(); i++) {
                    Logger.d(data.getPrintPrizes().get(i).getSizes());
                    switch (i) {
                        case 0:
                            btAdd1.setVisibility(View.VISIBLE);
                            btAdd1.setText(data.getPrintPrizes().get(i).getName());
                            btAdd1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    layoutAddPrint.setVisibility(View.VISIBLE);
                                    OrderPhoto orderPhoto = new OrderPhoto();
                                    orderPhoto.setId("empty_" + System.currentTimeMillis());
                                    orderPhoto.setSizes(data.getBasePhotoItem().getSizes());
                                    orderPhoto.setColor(data.getBasePhotoItem().getColor());
                                    orderPhoto.setSizes(data.getPrintPrizes().get(0).getSizes());
                                    orderPhoto.setSize(data.getPrintPrizes().get(0).getName());
                                    orderPhoto.setPrice(data.getPrintPrizes().get(0).getPrice());
                                    orderPhoto.setPhotoOrderId(data.getBasePhotoItem().getOrderId());
                                    orderPhoto.setServiceOrderId(data.getBasePhotoItem().getServiceOrderId());
                                    orderPhoto.setBaseOrderId(data.getBasePhotoItem().getBaseOrderId());
                                    data.getPhotoItems().add(orderPhoto);
                                    showPhotoItems(data);
                                }
                            });
                            break;
                        case 1:
                            btAdd2.setVisibility(View.VISIBLE);
                            btAdd2.setText(data.getPrintPrizes().get(i).getName());
                            btAdd2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    layoutAddPrint.setVisibility(View.VISIBLE);
                                    OrderPhoto orderPhoto = new OrderPhoto();
                                    orderPhoto.setSizes(data.getBasePhotoItem().getSizes());
                                    orderPhoto.setId("empty_" + System.currentTimeMillis());
                                    orderPhoto.setSizes(data.getPrintPrizes().get(1).getSizes());
                                    orderPhoto.setColor(data.getBasePhotoItem().getColor());
                                    orderPhoto.setSize(data.getPrintPrizes().get(1).getName());
                                    orderPhoto.setPrice(data.getPrintPrizes().get(1).getPrice());
                                    orderPhoto.setPhotoOrderId(data.getBasePhotoItem().getOrderId());
                                    orderPhoto.setServiceOrderId(data.getBasePhotoItem().getServiceOrderId());
                                    orderPhoto.setBaseOrderId(data.getBasePhotoItem().getBaseOrderId());
                                    data.getPhotoItems().add(orderPhoto);
                                    showPhotoItems(data);

                                }
                            });
                            break;
                        case 2:
                            btAdd3.setVisibility(View.VISIBLE);
                            btAdd3.setText(data.getPrintPrizes().get(i).getName());
                            btAdd3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    OrderPhoto orderPhoto = new OrderPhoto();
                                    orderPhoto.setSizes(data.getPrintPrizes().get(2).getSizes());
                                    orderPhoto.setId("empty_" + System.currentTimeMillis());
                                    orderPhoto.setColor(data.getBasePhotoItem().getColor());
                                    orderPhoto.setSize(data.getPrintPrizes().get(2).getName());
                                    orderPhoto.setPrice(data.getPrintPrizes().get(2).getPrice());
                                    orderPhoto.setPhotoOrderId(data.getBasePhotoItem().getOrderId());
                                    orderPhoto.setServiceOrderId(data.getBasePhotoItem().getServiceOrderId());
                                    orderPhoto.setBaseOrderId(data.getBasePhotoItem().getBaseOrderId());
                                    data.getPhotoItems().add(orderPhoto);
                                    showPhotoItems(data);
                                }
                            });
                            break;
                    }

                }
            }
            showPhotoItems(data);
        }

        private void showPhotoItems(final PhotoServiceItem data) {
            if (data.getPhotoItems() == null || data.getPhotoItems().isEmpty()) {
                layoutAddPrint.setVisibility(View.GONE);
            } else {
                photoRyvAdapter.setItemDoLisenter(new AdapterItemDoLisenter() {
                    @Override
                    public void doItem(int type, final int position) {
                        switch (type) {
                            case AdapterItemDoLisenter.TYPE_DELETE:
                                PromptDialogUtil.getInstance().getPromptDialog().showWarnAlert("是否确认删除？", new PromptButton("确定", new PromptButtonListener() {

                                    @Override
                                    public void onClick(PromptButton promptButton) {
                                        if (!StringUtils.isEmpty(data.getPhotoItems().get(position).getOrderId())) {
                                            PromptDialogUtil.getInstance().getPromptDialog().dismiss();
                                            OrderDeleteEvent orderDeleteEvent = new OrderDeleteEvent.Builder()
                                                    .id(data.getPhotoItems().get(position).getId())
                                                    .type(Constants.TYPE_PAYBACK_SERVICE_ADD)
                                                    .build();
                                            EventBus.getDefault().post(orderDeleteEvent);
                                        }
                                        data.getPhotoItems().remove(position);
                                        photoRyvAdapter.clear();
                                        if (!data.getPhotoItems().isEmpty()) {
                                            photoRyvAdapter.addAll(data.getPhotoItems());
                                            double addPrintTotalPrice = 0d;
                                            for (int i = 0; i < data.getPhotoItems().size(); i++) {
                                                try {
                                                    addPrintTotalPrice = CalculateUtils.add(Double.toString(addPrintTotalPrice), data.getPhotoItems().get(i).getPrice());
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                    Logger.e("加印价格格式化异常");
                                                }
                                            }
                                            tvAddPrintTotalPrice.setText("小计：¥" + addPrintTotalPrice);
                                        } else {
                                            tvAddPrintTotalPrice.setText("小计：¥" + 0);
                                            layoutAddPrint.setVisibility(View.GONE);
                                        }
                                    }
                                }), true);
                                break;
                            case AdapterItemDoLisenter.TYPE_PAYBACK:
                                PromptDialogUtil.getInstance().getPromptDialog().showWarnAlert("是否确认退款？", new PromptButton("确定", new PromptButtonListener() {

                                    @Override
                                    public void onClick(PromptButton promptButton) {
                                        OrderPhoto orderPhoto = data.getPhotoItems().get(position);
                                        OrderPayRefundEvent orderPayRefundEvent = new OrderPayRefundEvent.Builder()
                                                .id(orderPhoto.getId())
                                                .count(orderPhoto.getCount() - orderPhoto.getRefundCount())
                                                .price(orderPhoto.getPrice())
                                                .type(Constants.TYPE_PAYBACK_SERVICE_ADD)
                                                .payChannel(orderPhoto.getPayChannel())
                                                .build();
                                        EventBus.getDefault().post(orderPayRefundEvent);
                                    }
                                }), true);
                                break;
                        }

                    }
                });
                layoutAddPrint.setVisibility(View.VISIBLE);
                if (data.getPhotoItems() != null && !data.getPhotoItems().isEmpty()) {
                    double addPrintTotalPrice = 0d;
                    for (int i = 0; i < data.getPhotoItems().size(); i++) {
                        try {
                            addPrintTotalPrice = CalculateUtils.add(addPrintTotalPrice + "",data.getPhotoItems().get(i).getPrice());
                            OrderPhoto orderPhoto = data.getPhotoItems().get(i);
                            OrderServicePriceEvent orderServicePriceEvent = new OrderServicePriceEvent.Builder()
                                    .id(orderPhoto.getOrderId())
                                    .price(orderPhoto.getPrice())
                                    .status(orderPhoto.getStatus())
                                    .count(orderPhoto.getCount() + "").build();
                            EventBus.getDefault().post(orderServicePriceEvent);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Logger.e("加印价格格化式异常");
                        }
                    }
                    tvAddPrintTotalPrice.setText("小计：¥" + addPrintTotalPrice);
                    photoRyvAdapter.clear();
                    photoRyvAdapter.addAll(data.getPhotoItems());
                }
            }
        }
    }
}
