package com.sj.skyblue.manager.handler;

import com.alibaba.fastjson.JSON;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.StringUtils;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.OrderDetailEntity;
import com.sj.skyblue.entity.order.OrderInfo;
import com.sj.skyblue.entity.order.OrderPhoto;
import com.sj.skyblue.entity.order.OrderPhotoService;
import com.sj.skyblue.entity.order.OrderProductService;
import com.sj.skyblue.entity.order.OrderService;
import com.sj.skyblue.entity.order.PhotoServiceItem;
import com.sj.skyblue.entity.order.base.OrderServiceBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunj on 2018/11/14.
 */

public class OrderDetailHandler {
    private static final OrderDetailHandler ourInstance = new OrderDetailHandler();

    public static OrderDetailHandler getInstance() {
        return ourInstance;
    }

    List<OrderServiceBase> orderServiceList = new ArrayList<>();

    private OrderDetailHandler() {
    }

    public void handler(OrderDetailEntity result) {
        orderServiceList.clear();
        if (result != null && result.getServices() != null && !result.getServices().isEmpty()) {
            OrderInfo orderInfo = result.getOrderInfo();
            String orderId = orderInfo.getOrderId();
            for (OrderService orderService : result.getServices()) {
                String orderServiceId = orderService.getOrderServiceId();
                String baseServicePrice = orderService.getTierPrice();
                String isExtra = orderService.getIsExtra();
                String serviceName = orderService.getName();
                String prePrice = orderService.getPrePrice();
                List<OrderService.SizeColor> sizes = orderService.getSize();
                List<OrderService.SizeColor> colors = orderService.getColor();
                List<OrderService.PrintPrizesBean> printPrizesBeans = orderService.getPrintPrizes();
                //服务订单
                OrderPhotoService orderPhotoService = new OrderPhotoService();
                orderPhotoService.setServiceOrderId(orderServiceId);
                orderPhotoService.setBaseOrderId(orderId);
                orderPhotoService.setName(serviceName);
                orderPhotoService.setServiceId(orderService.getServiceId());
                orderPhotoService.setIsExtra(isExtra);
                orderPhotoService.setPrePrice(prePrice);
                orderPhotoService.setTierPrice(baseServicePrice);
                orderPhotoService.setTierAddPrice(orderService.getTierAddPrice());

                List<PhotoServiceItem> photoServiceItems = new ArrayList<>();

                //基础照片服务订单
                if (orderService.getBasePrint() != null) {
                    OrderService.BasePrintBean basePrintBean = orderService.getBasePrint();
                    PhotoServiceItem photoServiceItem = new PhotoServiceItem();
                    photoServiceItem.setId(basePrintBean.getId());
                    photoServiceItem.setTierPrice(orderService.getTierPrice());
                    photoServiceItem.setTierAddPrice(orderService.getTierAddPrice());
                    photoServiceItem.setPrePrice(prePrice);
                    photoServiceItem.setPrintPrizes(printPrizesBeans);
                    photoServiceItem.setSizes(sizes);
                    photoServiceItem.setColors(colors);
                    photoServiceItem.setPicUrl(basePrintBean.getPicUrl());
                    photoServiceItem.setIsExtra(basePrintBean.getIsExtra());
                    photoServiceItem.setServiceName(serviceName);
                    photoServiceItem.setBaseOrderId(orderId);
                    photoServiceItem.setServiceOrderId(orderServiceId);
                    photoServiceItem.setPhotoOrderId(basePrintBean.getOrderId());

                    OrderPhoto baseOrderPhoto = new OrderPhoto();
                    baseOrderPhoto.setBaseOrderId(orderId);
                    baseOrderPhoto.setServiceName(photoServiceItem.getServiceName());
                    baseOrderPhoto.setServiceOrderId(orderServiceId);
                    baseOrderPhoto.setPhotoOrderId(basePrintBean.getOrderId());
                    baseOrderPhoto.setSize(StringUtils.isEmpty(basePrintBean.getSize()) ? sizes.get(0).getName() : basePrintBean.getSize());
                    baseOrderPhoto.setColor(StringUtils.isEmpty(basePrintBean.getColor()) ? colors.get(0).getName() : basePrintBean.getColor());
                    baseOrderPhoto.setPrice(basePrintBean.getPrice().equals("0") ? baseServicePrice : basePrintBean.getPrice());
                    baseOrderPhoto.setStatus(basePrintBean.getStatus());
                    baseOrderPhoto.setPayChannelName(basePrintBean.getPayChannelName());
                    baseOrderPhoto.setPayChannel(basePrintBean.getPayChannel());
                    baseOrderPhoto.setOrderNum(basePrintBean.getOrderNum());
                    baseOrderPhoto.setOrderId(basePrintBean.getOrderId());
                    baseOrderPhoto.setId(basePrintBean.getId());
                    baseOrderPhoto.setCount(basePrintBean.getCount());
                    baseOrderPhoto.setPeopleCount(basePrintBean.getPeopleCount());
                    baseOrderPhoto.setRefundCount(basePrintBean.getRefundCount());
                    baseOrderPhoto.setPaidCount(basePrintBean.getPaidCount());

                    photoServiceItem.setBasePhotoItem(baseOrderPhoto);
                    orderPhotoService.setBaseColor(baseOrderPhoto.getColor());

                    //基础照片加印订单
                    if (basePrintBean.getOrderExtra() != null) {
                        List<OrderPhoto> orderPhotos = new ArrayList<>();
                        for (OrderService.BasePrintBean.OrderExtraBean orderExtraBean : basePrintBean.getOrderExtra()) {
                            OrderPhoto orderPhoto = new OrderPhoto();
                            orderPhoto.setServiceName(photoServiceItem.getServiceName());
                            orderPhoto.setBaseOrderId(orderId);
                            orderPhoto.setServiceOrderId(orderServiceId);
                            orderPhoto.setPhotoOrderId(basePrintBean.getOrderId());
                            orderPhoto.setSize(orderExtraBean.getSize());
                            orderPhoto.setColor(orderExtraBean.getColor());
                            orderPhoto.setPrice(orderExtraBean.getPrice());
                            orderPhoto.setStatus(orderExtraBean.getStatus());
                            orderPhoto.setPayChannelName(orderExtraBean.getPayChannelName());
                            orderPhoto.setPayChannel(orderExtraBean.getPayChannel());
                            orderPhoto.setOrderNum(orderExtraBean.getOrderNum());
                            orderPhoto.setOrderId(orderExtraBean.getOrderId());
                            orderPhoto.setId(orderExtraBean.getId());
                            orderPhoto.setCount(orderExtraBean.getCount());
                            orderPhoto.setRefundCount(orderExtraBean.getRefundCount());
                            orderPhoto.setPaidCount(orderExtraBean.getPaidCount());
                            orderPhotos.add(orderPhoto);
                        }
                        photoServiceItem.setPhotoItems(orderPhotos);
                    }
                    photoServiceItems.add(photoServiceItem);
                }
                //加选照片服务订单
                if (orderService.getExtPrint() != null) {
                    for (int i = 0; i < orderService.getExtPrint().size(); i++) {
                        //基础照片服务订单
                        OrderService.BasePrintBean basePrintBean = orderService.getExtPrint().get(i);
                        PhotoServiceItem photoServiceItem = new PhotoServiceItem();
                        photoServiceItem.setId(basePrintBean.getId());
                        photoServiceItem.setPrePrice(prePrice);
                        photoServiceItem.setTierPrice(orderService.getTierPrice());
                        photoServiceItem.setTierAddPrice(orderService.getTierAddPrice());
                        photoServiceItem.setPrintPrizes(printPrizesBeans);
                        photoServiceItem.setSizes(sizes);
                        photoServiceItem.setColors(colors);
                        photoServiceItem.setPicUrl(basePrintBean.getPicUrl());
                        photoServiceItem.setIsExtra(basePrintBean.getIsExtra());
//                        photoServiceItem.setServiceName("加选" + (i + 1));
                        photoServiceItem.setServiceName("加选");
                        photoServiceItem.setBaseOrderId(orderId);
                        photoServiceItem.setServiceOrderId(orderServiceId);
                        photoServiceItem.setPhotoOrderId(basePrintBean.getOrderId());

                        OrderPhoto baseOrderPhoto = new OrderPhoto();
                        baseOrderPhoto.setBaseOrderId(orderId);
                        baseOrderPhoto.setServiceName(photoServiceItem.getServiceName());
                        baseOrderPhoto.setServiceOrderId(orderServiceId);
                        baseOrderPhoto.setPhotoOrderId(basePrintBean.getOrderId());
                        baseOrderPhoto.setSize(StringUtils.isEmpty(basePrintBean.getSize()) ? sizes.get(0).getName() : basePrintBean.getSize());
                        baseOrderPhoto.setColor(StringUtils.isEmpty(basePrintBean.getColor()) ? colors.get(0).getName() : basePrintBean.getColor());
                        baseOrderPhoto.setPrice(basePrintBean.getPrice().equals("0") ? baseServicePrice : basePrintBean.getPrice());
                        baseOrderPhoto.setStatus(basePrintBean.getStatus());
                        baseOrderPhoto.setPayChannelName(basePrintBean.getPayChannelName());
                        baseOrderPhoto.setPayChannel(basePrintBean.getPayChannel());
                        baseOrderPhoto.setOrderNum(basePrintBean.getOrderNum());
                        baseOrderPhoto.setOrderId(basePrintBean.getOrderId());
                        baseOrderPhoto.setId(basePrintBean.getId());
                        baseOrderPhoto.setCount(basePrintBean.getCount());
                        baseOrderPhoto.setRefundCount(basePrintBean.getRefundCount());
                        baseOrderPhoto.setPaidCount(basePrintBean.getPaidCount());
                        baseOrderPhoto.setPeopleCount(basePrintBean.getPeopleCount());

//                        if (!Constants.TYPE_ADD_SERVICE.equals(orderService.getType())) {
//                            baseOrderPhoto.setCount(orderService.getExtPrint().size());
//                            photoServiceItem.setBasePhotoItem(baseOrderPhoto);
//                            orderPhotoService.setBaseColor(baseOrderPhoto.getColor());
//                            break;
//                        } else
                        if (basePrintBean.getOrderExtra() != null) {
                            List<OrderPhoto> orderPhotos = new ArrayList<>();
                            for (OrderService.BasePrintBean.OrderExtraBean orderExtraBean : basePrintBean.getOrderExtra()) {
                                OrderPhoto orderPhoto = new OrderPhoto();
                                orderPhoto.setServiceName(photoServiceItem.getServiceName());
                                orderPhoto.setBaseOrderId(orderId);
                                orderPhoto.setServiceOrderId(orderServiceId);
                                orderPhoto.setPhotoOrderId(basePrintBean.getOrderId());
                                orderPhoto.setSize(orderExtraBean.getSize());
                                orderPhoto.setColor(orderExtraBean.getColor());
                                orderPhoto.setPrice(orderExtraBean.getPrice());
                                orderPhoto.setStatus(orderExtraBean.getStatus());
                                orderPhoto.setPayChannelName(orderExtraBean.getPayChannelName());
                                orderPhoto.setPayChannel(orderExtraBean.getPayChannel());
                                orderPhoto.setOrderNum(orderExtraBean.getOrderNum());
                                orderPhoto.setCount(orderExtraBean.getCount());
                                orderPhoto.setRefundCount(orderExtraBean.getRefundCount());
                                orderPhoto.setPaidCount(orderExtraBean.getPaidCount());
                                orderPhoto.setOrderId(orderExtraBean.getOrderId());
                                orderPhoto.setId(orderExtraBean.getId());
                                orderPhotos.add(orderPhoto);
                            }
                            photoServiceItem.setPhotoItems(orderPhotos);
                        }
                        photoServiceItem.setBasePhotoItem(baseOrderPhoto);
                        orderPhotoService.setBaseColor(baseOrderPhoto.getColor());
                        photoServiceItems.add(photoServiceItem);
                    }
                }
                orderPhotoService.setPhotoServiceItems(photoServiceItems);
                orderPhotoService.setType(orderService.getType());
                orderServiceList.add(orderPhotoService);
            }
            if (result != null && result.getProducts() != null && !result.getProducts().isEmpty()) {
                OrderProductService orderProductService = new OrderProductService();
                orderProductService.setBaseOrderId(result.getOrderInfo().getOrderId());
                orderProductService.setOrderProductList(result.getProducts());
                orderServiceList.add(orderProductService);
            }
        }
    }

    public List<OrderServiceBase> getOrderServiceList() {
        return orderServiceList;
    }

}
