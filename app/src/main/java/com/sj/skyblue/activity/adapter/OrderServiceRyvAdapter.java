package com.sj.skyblue.activity.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
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
import com.sj.skyblue.entity.order.OrderPhotoService;
import com.sj.skyblue.entity.order.OrderProduct;
import com.sj.skyblue.entity.order.OrderProductService;
import com.sj.skyblue.entity.order.PhotoServiceItem;
import com.sj.skyblue.entity.order.base.OrderServiceBase;
import com.sj.skyblue.event.OrderChangeEvent;
import com.sj.skyblue.event.OrderDeleteEvent;
import com.sj.skyblue.event.OrderPayRefundEvent;
import com.sj.skyblue.event.OrderServicePriceEvent;
import com.sj.skyblue.lisenter.AdapterItemAddLisenter;
import com.sj.skyblue.lisenter.AdapterItemDoLisenter;
import com.sj.skyblue.utils.PromptDialogUtil;

import org.greenrobot.eventbus.EventBus;

import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;

/**
 * Created by Sunj on 2018/7/8.
 */

public class OrderServiceRyvAdapter extends RecyclerArrayAdapter<OrderServiceBase> {
    public static Context mContext;

    public OrderServiceRyvAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new OrderAddServiceRyvHolder(parent);
        } else if (viewType == 1) {
            return new OrderGroupRyvHolder(parent);
        } else if (viewType == 2) {
            return new OrderProductRyvHolder(parent);
        } else {
            return new OrderOtherRyvHolder(parent);
        }
    }

    @Override
    public int getViewType(int position) {
        if (getItem(position) instanceof OrderPhotoService) {
            if (Constants.TYPE_ADD_SERVICE.equals(((OrderPhotoService) getItem(position)).getType())) {
                return 0;
            } else if (Constants.TYPE_GROUP_SERVICE.equals(((OrderPhotoService) getItem(position)).getType())) {
                return 1;
            } else {
                return 3;
            }
        } else {
            return 2;
        }
    }

    /**
     * 加印类型
     */
    public static class OrderAddServiceRyvHolder extends BaseViewHolder<OrderPhotoService> {

        EasyRecyclerView rylView;
        PhotoServiceRyvAdapter photoServiceRyvAdapter;

        public OrderAddServiceRyvHolder(ViewGroup parent) {
            super(parent, R.layout.item_order_service);
            rylView = $(R.id.ryl_view);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            rylView.setLayoutManager(layoutManager);
            DividerDecoration dividerDecoration = new DividerDecoration(mContext.getResources().getColor(R.color.transparent), 20, 0, 0);
            dividerDecoration.setDrawLastItem(false);
            rylView.addItemDecoration(dividerDecoration);

        }

        @Override
        public void setData(final OrderPhotoService data) {
            super.setData(data);
            photoServiceRyvAdapter = new PhotoServiceRyvAdapter(mContext);
            rylView.setAdapter(photoServiceRyvAdapter);
            photoServiceRyvAdapter.addAll(data.getPhotoServiceItems());
            photoServiceRyvAdapter.setItemAddLisenter(new AdapterItemAddLisenter() {
                @Override
                public void addItem(PhotoServiceItem item) {
                    data.getPhotoServiceItems().add(item);
                    updateItems(data);
                }

                @Override
                public void updateColor(String color) {
                    for (PhotoServiceItem photoServiceItem : data.getPhotoServiceItems()) {
                        photoServiceItem.getBasePhotoItem().setColor(color);
                        for (OrderPhoto orderPhoto : photoServiceItem.getPhotoItems()) {
                            orderPhoto.setColor(color);
                        }
                    }
                    updateItems(data);
                }
            });
            photoServiceRyvAdapter.setItemDoLisenter(new AdapterItemDoLisenter() {
                @Override
                public void doItem(int type, final int position) {
                    switch (type) {
                        case AdapterItemDoLisenter.TYPE_PAYBACK:
                            PromptDialogUtil.getInstance().getPromptDialog().showWarnAlert("是否确认退款？", new PromptButton("确定", new PromptButtonListener() {

                                @Override
                                public void onClick(PromptButton promptButton) {
                                    PhotoServiceItem photoServiceItem = data.getPhotoServiceItems().get(position);
                                    OrderPayRefundEvent orderPayRefundEvent = new OrderPayRefundEvent.Builder()
                                            .id(photoServiceItem.getId())
                                            .count(photoServiceItem.getBasePhotoItem().getCount() - photoServiceItem.getBasePhotoItem().getRefundCount())
                                            .price(photoServiceItem.getBasePhotoItem().getPrice())
                                            .type(Constants.TYPE_PAYBACK_SERVICE_BASE)
                                            .payChannel(photoServiceItem.getBasePhotoItem().getPayChannel())
                                            .build();
                                    EventBus.getDefault().post(orderPayRefundEvent);
                                }
                            }), true);
                            break;
                        case AdapterItemDoLisenter.TYPE_CHANGE:
                            OrderChangeEvent orderChangeEvent = new OrderChangeEvent(data.getPhotoServiceItems().get(position).getServiceOrderId());
                            EventBus.getDefault().post(orderChangeEvent);
                            break;
                        case AdapterItemDoLisenter.TYPE_DELETE:
                            PromptDialogUtil.getInstance().getPromptDialog().showWarnAlert("是否确认删除？", new PromptButton("确定", new PromptButtonListener() {

                                @Override
                                public void onClick(PromptButton promptButton) {
                                    if (position==0||!StringUtils.isEmpty(data.getPhotoServiceItems().get(position).getPhotoOrderId())) {
                                        PromptDialogUtil.getInstance().getPromptDialog().dismiss();
                                        OrderDeleteEvent orderDeleteEvent = new OrderDeleteEvent.Builder()
                                                .id(position==0?data.getPhotoServiceItems().get(position).getServiceOrderId():data.getPhotoServiceItems().get(position).getId())
                                                .type(position==0?Constants.TYPE_PAYBACK_SERVICE:Constants.TYPE_PAYBACK_SERVICE_BASE)
                                                .positon(getDataPosition())
                                                .build();
                                        EventBus.getDefault().post(orderDeleteEvent);
                                    }
                                    if (position!=0){
                                        data.getPhotoServiceItems().remove(position);
                                        updateItems(data);
                                        updateItems(data);
                                    }
                                }
                            }), true);
                            break;
                    }

                }
            });
        }

        private void updateItems(OrderPhotoService data) {
            photoServiceRyvAdapter.clear();
            photoServiceRyvAdapter.addAll(data.getPhotoServiceItems());
        }

    }

    /**
     * 普通类型
     */
    public static class OrderOtherRyvHolder extends BaseViewHolder<OrderPhotoService> {

        EasyRecyclerView rylView;
        TextView tvTotalPrice;
        TextView tvExt;
        TextView tvServiceName;
        OrderOtherRyvAdapter otherRyvAdapter;

        public OrderOtherRyvHolder(ViewGroup parent) {
            super(parent, R.layout.item_photo_other_service);
            tvServiceName = $(R.id.tv_service_name);
            rylView = $(R.id.ryl_view);
            tvTotalPrice = $(R.id.tv_total_price);
            tvExt = $(R.id.bt_ext);
        }

        @Override
        public void setData(final OrderPhotoService data) {
            super.setData(data);
            tvServiceName.setText(data.getName());
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            rylView.setLayoutManager(layoutManager);
            DividerDecoration dividerDecoration = new DividerDecoration(mContext.getResources().getColor(R.color.divider_color), 1, 0, 0);
            dividerDecoration.setDrawLastItem(false);
            rylView.addItemDecoration(dividerDecoration);
            otherRyvAdapter = new OrderOtherRyvAdapter(mContext, new AdapterItemDoLisenter() {
                @Override
                public void doItem(int type, final int position) {
                    switch (type) {
                        case AdapterItemDoLisenter.TYPE_DELETE:
                            PromptDialogUtil.getInstance().getPromptDialog().showWarnAlert("是否确认删除？", new PromptButton("确定", new PromptButtonListener() {

                                @Override
                                public void onClick(PromptButton promptButton) {
                                    if (position==0||!StringUtils.isEmpty(data.getPhotoServiceItems().get(position).getPhotoOrderId())) {
                                        PromptDialogUtil.getInstance().getPromptDialog().dismiss();
                                        OrderDeleteEvent orderDeleteEvent = new OrderDeleteEvent.Builder()
                                                .id(position==0?data.getPhotoServiceItems().get(position).getServiceOrderId():data.getPhotoServiceItems().get(position).getId())
                                                .type(position==0?Constants.TYPE_PAYBACK_SERVICE:Constants.TYPE_PAYBACK_SERVICE_BASE)
                                                .positon(getDataPosition())
                                                .build();
                                        EventBus.getDefault().post(orderDeleteEvent);
                                    }
                                    if (position!=0){
                                        data.getPhotoServiceItems().remove(position);
                                        updateItems(data);
                                        updateTotalPrice(data);
                                    }
                                }
                            }), true);
                            break;
                        case AdapterItemDoLisenter.TYPE_PAYBACK:
                            PromptDialogUtil.getInstance().getPromptDialog().showWarnAlert("是否确认退款？", new PromptButton("确定", new PromptButtonListener() {

                                @Override
                                public void onClick(PromptButton promptButton) {
                                    PhotoServiceItem photoServiceItem = data.getPhotoServiceItems().get(position);
                                    OrderPayRefundEvent orderPayRefundEvent = new OrderPayRefundEvent.Builder()
                                            .id(photoServiceItem.getId())
                                            .count(photoServiceItem.getBasePhotoItem().getCount() - photoServiceItem.getBasePhotoItem().getRefundCount())
                                            .price(photoServiceItem.getBasePhotoItem().getPrice())
                                            .type(Constants.TYPE_PAYBACK_SERVICE_BASE)
                                            .payChannel(photoServiceItem.getBasePhotoItem().getPayChannel())
                                            .build();
                                    EventBus.getDefault().post(orderPayRefundEvent);
                                }
                            }), true);
                            break;
                        case AdapterItemDoLisenter.TYPE_UPDATE:
                            updateTotalPrice(data);
                            break;
                        case AdapterItemDoLisenter.TYPE_CHANGE:
                            OrderChangeEvent orderChangeEvent = new OrderChangeEvent(data.getPhotoServiceItems().get(position).getServiceOrderId());
                            EventBus.getDefault().post(orderChangeEvent);
                            break;
                    }

                }
            });
            rylView.setAdapter(otherRyvAdapter);
            updateItems(data);
            tvExt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.getPhotoServiceItems().size() > 1 && StringUtils.isEmpty(data.getPhotoServiceItems().get(data.getPhotoServiceItems().size() - 1).getBasePhotoItem().getOrderNum())) {
                        PhotoServiceItem extPhotoServiceItem = data.getPhotoServiceItems().get(data.getPhotoServiceItems().size() - 1);
                        extPhotoServiceItem.getBasePhotoItem().setCount(extPhotoServiceItem.getBasePhotoItem().getCount() + 1);
                    } else {
                        PhotoServiceItem basePhotoServiceItem = data.getPhotoServiceItems().get(0);
                        PhotoServiceItem photoServiceItem = new PhotoServiceItem();
                        photoServiceItem.setServiceName("加选");
                        photoServiceItem.setIsExtra("1");

                        OrderPhoto orderPhoto = new OrderPhoto();
                        orderPhoto.setId("empty_" + System.currentTimeMillis());
                        orderPhoto.setServiceName(photoServiceItem.getServiceName());
                        orderPhoto.setPrice(data.getTierAddPrice());
                        orderPhoto.setCount(1);
                        orderPhoto.setPeopleCount(1);
                        orderPhoto.setSize(basePhotoServiceItem.getBasePhotoItem().getSize());
                        orderPhoto.setColor(basePhotoServiceItem.getBasePhotoItem().getColor());
                        photoServiceItem.setBasePhotoItem(orderPhoto);
                        data.getPhotoServiceItems().add(photoServiceItem);
                    }
                    updateItems(data);
                    updateTotalPrice(data);
                }
            });
            updateTotalPrice(data);
        }

        private void updateItems(OrderPhotoService data) {
            otherRyvAdapter.clear();
            otherRyvAdapter.addAll(data.getPhotoServiceItems());
        }

        private void updateTotalPrice(OrderPhotoService data) {
            double totalPrice = 0d;
            double totalPrePrice = CalculateUtils.mul(data.getPrePrice(), "1");
            for (int i = 0; i < data.getPhotoServiceItems().size(); i++) {
                PhotoServiceItem photoServiceItem = data.getPhotoServiceItems().get(i);
                OrderPhoto orderPhoto = photoServiceItem.getBasePhotoItem();
                OrderServicePriceEvent orderServicePriceEvent = new OrderServicePriceEvent.Builder()
                        .id(orderPhoto.getId())
                        .price(orderPhoto.getPrice())
                        .status(orderPhoto.getStatus())
                        .count(orderPhoto.getCount() + "").build();
                EventBus.getDefault().post(orderServicePriceEvent);
                totalPrice = CalculateUtils.add(totalPrice + "", Double.toString(CalculateUtils.mul(orderPhoto.getPrice(), Integer.toString(orderPhoto.getCount()))));
            }
            tvTotalPrice.setText("小计：¥" + totalPrice+(totalPrePrice>0d?"(优惠 ¥"+totalPrePrice+")":""));
        }
    }

    /**
     * 合照类型
     */
    public static class OrderGroupRyvHolder extends BaseViewHolder<OrderPhotoService> {

        EasyRecyclerView rylView;
        TextView tvTotalPrice;
        TextView tvExt;
        TextView tvServiceName;
        OrderGroupRyvAdapter groupRyvAdapter;

        public OrderGroupRyvHolder(ViewGroup parent) {
            super(parent, R.layout.item_photo_group_service);
            tvServiceName = $(R.id.tv_service_name);
            rylView = $(R.id.ryl_view);
            tvExt = $(R.id.bt_ext);
            tvTotalPrice = $(R.id.tv_total_price);

        }

        @Override
        public void setData(final OrderPhotoService data) {
            super.setData(data);
            tvServiceName.setText(data.getName());
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            rylView.setLayoutManager(layoutManager);
            DividerDecoration dividerDecoration = new DividerDecoration(mContext.getResources().getColor(R.color.divider_color), 1, 0, 0);
            dividerDecoration.setDrawLastItem(false);
            rylView.addItemDecoration(dividerDecoration);
            groupRyvAdapter = new OrderGroupRyvAdapter(mContext, new AdapterItemDoLisenter() {
                @Override
                public void doItem(int type, final int position) {
                    switch (type) {
                        case AdapterItemDoLisenter.TYPE_DELETE:
                            PromptDialogUtil.getInstance().getPromptDialog().showWarnAlert("是否确认删除？", new PromptButton("确定", new PromptButtonListener() {

                                @Override
                                public void onClick(PromptButton promptButton) {
                                    if (position==0||!StringUtils.isEmpty(data.getPhotoServiceItems().get(position).getPhotoOrderId())) {
                                        PromptDialogUtil.getInstance().getPromptDialog().dismiss();
                                        OrderDeleteEvent orderDeleteEvent = new OrderDeleteEvent.Builder()
                                                .id(position==0?data.getPhotoServiceItems().get(position).getServiceOrderId():data.getPhotoServiceItems().get(position).getId())
                                                .type(position==0?Constants.TYPE_PAYBACK_SERVICE:Constants.TYPE_PAYBACK_SERVICE_BASE)
                                                .positon(getDataPosition())
                                                .build();
                                        EventBus.getDefault().post(orderDeleteEvent);
                                    }
                                    if (position!=0){
                                        data.getPhotoServiceItems().remove(position);
                                        updateItems(data);
                                        updateTotalPrice(data);
                                    }
                                }
                            }), true);
                            break;
                        case AdapterItemDoLisenter.TYPE_PAYBACK:
                            PromptDialogUtil.getInstance().getPromptDialog().showWarnAlert("是否确认退款？", new PromptButton("确定", new PromptButtonListener() {

                                @Override
                                public void onClick(PromptButton promptButton) {
                                    PhotoServiceItem photoServiceItem = data.getPhotoServiceItems().get(position);
                                    OrderPayRefundEvent orderPayRefundEvent = new OrderPayRefundEvent.Builder()
                                            .id(photoServiceItem.getId())
                                            .count(photoServiceItem.getBasePhotoItem().getCount() - photoServiceItem.getBasePhotoItem().getRefundCount())
                                            .price(photoServiceItem.getBasePhotoItem().getPrice())
                                            .type(Constants.TYPE_PAYBACK_SERVICE_BASE)
                                            .payChannel(photoServiceItem.getBasePhotoItem().getPayChannel())
                                            .build();
                                    EventBus.getDefault().post(orderPayRefundEvent);
                                }
                            }), true);
                            break;
                        case AdapterItemDoLisenter.TYPE_UPDATE:
                            updateTotalPrice(data);
                            break;
                        case AdapterItemDoLisenter.TYPE_CHANGE:
                            OrderChangeEvent orderChangeEvent = new OrderChangeEvent(data.getPhotoServiceItems().get(position).getServiceOrderId());
                            EventBus.getDefault().post(orderChangeEvent);
                            break;
                    }

                }
            });
            rylView.setAdapter(groupRyvAdapter);
            updateItems(data);
            tvExt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.getPhotoServiceItems().size() > 1 && StringUtils.isEmpty(data.getPhotoServiceItems().get(data.getPhotoServiceItems().size() - 1).getBasePhotoItem().getOrderNum())) {
                        PhotoServiceItem extPhotoServiceItem = data.getPhotoServiceItems().get(data.getPhotoServiceItems().size() - 1);
                        extPhotoServiceItem.getBasePhotoItem().setCount(extPhotoServiceItem.getBasePhotoItem().getCount() + 1);
                    } else {
                        PhotoServiceItem basePhotoServiceItem = data.getPhotoServiceItems().get(0);
                        PhotoServiceItem photoServiceItem = new PhotoServiceItem();
                        photoServiceItem.setServiceName("加选");
                        photoServiceItem.setIsExtra("1");

                        OrderPhoto orderPhoto = new OrderPhoto();
                        orderPhoto.setId("empty_" + System.currentTimeMillis());
                        orderPhoto.setServiceName(photoServiceItem.getServiceName());
                        orderPhoto.setPrice(data.getTierAddPrice());
                        orderPhoto.setCount(1);
                        orderPhoto.setPeopleCount(1);
                        orderPhoto.setSize(basePhotoServiceItem.getBasePhotoItem().getSize());
                        orderPhoto.setColor(basePhotoServiceItem.getBasePhotoItem().getColor());
                        photoServiceItem.setBasePhotoItem(orderPhoto);
                        data.getPhotoServiceItems().add(photoServiceItem);
                    }
                    updateItems(data);
                    updateTotalPrice(data);
                }
            });
            updateTotalPrice(data);
        }

        private void updateItems(OrderPhotoService data) {
            groupRyvAdapter.clear();
            groupRyvAdapter.addAll(data.getPhotoServiceItems());
        }

        private void updateTotalPrice(OrderPhotoService data) {
            double totalPrice = 0d;
            double totalPrePrice = CalculateUtils.mul(data.getPrePrice(), "1");
            for (int i = 0; i < data.getPhotoServiceItems().size(); i++) {
                PhotoServiceItem photoServiceItem = data.getPhotoServiceItems().get(i);
                OrderPhoto orderPhoto = photoServiceItem.getBasePhotoItem();
                OrderServicePriceEvent orderServicePriceEvent = new OrderServicePriceEvent.Builder()
                        .id(orderPhoto.getId())
                        .price(orderPhoto.getPrice())
                        .status(orderPhoto.getStatus())
                        .count(orderPhoto.getCount() + "").build();
                EventBus.getDefault().post(orderServicePriceEvent);
                totalPrice = CalculateUtils.add(totalPrice + "", Double.toString(CalculateUtils.mul(orderPhoto.getPrice(), Integer.toString(orderPhoto.getCount()))));
            }
            tvTotalPrice.setText("小计：¥" + totalPrice+(totalPrePrice>0d?"(优惠 ¥"+totalPrePrice+")":""));
        }
    }

    /**
     * 商品类型
     */
    public static class OrderProductRyvHolder extends BaseViewHolder<OrderProductService> {

        EasyRecyclerView rylView;
        OrderProductRyvAdapter productRyvAdapter;
        TextView tvTotalPrice;


        public OrderProductRyvHolder(ViewGroup parent) {
            super(parent, R.layout.item_product_service);
            rylView = $(R.id.ryl_view);
            tvTotalPrice = $(R.id.tv_total_price);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            rylView.setLayoutManager(layoutManager);
            DividerDecoration dividerDecoration = new DividerDecoration(mContext.getResources().getColor(R.color.divider_color), 1, 0, 0);
            dividerDecoration.setDrawLastItem(false);
            rylView.addItemDecoration(dividerDecoration);
            productRyvAdapter = new OrderProductRyvAdapter(mContext);
            rylView.setAdapter(productRyvAdapter);
        }

        @Override
        public void setData(final OrderProductService data) {
            super.setData(data);
            productRyvAdapter.addAll(data.getOrderProductList());
            productRyvAdapter.setItemDoLisenter(new AdapterItemDoLisenter() {
                @Override
                public void doItem(int type, final int position) {
                    switch (type) {
                        case AdapterItemDoLisenter.TYPE_DELETE:
                            PromptDialogUtil.getInstance().getPromptDialog().showWarnAlert("是否确认删除？", new PromptButton("确定", new PromptButtonListener() {

                                @Override
                                public void onClick(PromptButton promptButton) {
                                    PromptDialogUtil.getInstance().getPromptDialog().dismiss();
                                    OrderDeleteEvent orderDeleteEvent = new OrderDeleteEvent.Builder()
                                            .id(data.getOrderProductList().get(position).getId())
                                            .type(Constants.TYPE_PAYBACK_PRODUCT)
                                            .build();
                                    EventBus.getDefault().post(orderDeleteEvent);

                                    data.getOrderProductList().remove(position);
                                    productRyvAdapter.clear();
                                    if (!data.getOrderProductList().isEmpty()) {
                                        productRyvAdapter.addAll(data.getOrderProductList());
                                    }
                                    updateTotalPrice(data);
                                }
                            }), true);
                            break;
                        case AdapterItemDoLisenter.TYPE_PAYBACK:
                            PromptDialogUtil.getInstance().getPromptDialog().showWarnAlert("是否确认退款？", new PromptButton("确定", new PromptButtonListener() {

                                @Override
                                public void onClick(PromptButton promptButton) {
                                    OrderProduct orderProduct = data.getOrderProductList().get(position);
                                    OrderPayRefundEvent orderPayRefundEvent = new OrderPayRefundEvent.Builder()
                                            .id(orderProduct.getId())
                                            .count(orderProduct.getCount() - orderProduct.getRefundCount())
                                            .price(orderProduct.getPrice())
                                            .type(Constants.TYPE_PAYBACK_PRODUCT)
                                            .payChannel(orderProduct.getPayChannel())
                                            .build();
                                    EventBus.getDefault().post(orderPayRefundEvent);
                                }
                            }), true);

                            break;
                        case AdapterItemDoLisenter.TYPE_UPDATE:
                            updateTotalPrice(data);
                            break;
                    }

                }
            });
            updateTotalPrice(data);
        }

        private void updateTotalPrice(OrderProductService data) {
            double totalPrice = 0d;
            for (int i = 0; i < data.getOrderProductList().size(); i++) {
                OrderProduct orderProduct = data.getOrderProductList().get(i);
                OrderServicePriceEvent orderServicePriceEvent = new OrderServicePriceEvent.Builder()
                        .id(orderProduct.getServiceId())
                        .price(orderProduct.getPrice())
                        .status(orderProduct.getStatus())
                        .count(orderProduct.getCount() + "").build();
                EventBus.getDefault().post(orderServicePriceEvent);
                totalPrice = CalculateUtils.add(totalPrice + "", Double.toString(CalculateUtils.mul(data.getOrderProductList().get(i).getPrice(), Integer.toString(data.getOrderProductList().get(i).getCount()))));
            }
            tvTotalPrice.setText("小计：¥" + totalPrice);
        }
    }
}
