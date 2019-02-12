package com.sj.skyblue.manager.handler;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.StringUtils;
import com.sj.skyblue.entity.OrderData;
import com.sj.skyblue.entity.OrderDetailEntity;
import com.sj.skyblue.entity.OrderItem;
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

public class OrderItemHandler {
    private static final OrderItemHandler ourInstance = new OrderItemHandler();

    public static OrderItemHandler getInstance() {
        return ourInstance;
    }

    List<OrderItem> orderItems;

    private OrderItemHandler() {
    }

    public void handler(List<OrderData> result) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        } else {
            orderItems.clear();
        }
        if (null != result && !result.isEmpty()) {
            for (OrderData orderData : result) {
                List<OrderItem> orderItemList = orderData.getOrderInfo();
                if (null != orderItemList && !orderItemList.isEmpty()) {
                    for (OrderItem orderItem : orderItemList) {
                        orderItem.setTime(orderData.getTime());
                        orderItems.add(orderItem);
                    }
                }
                try {
                    int count = Integer.parseInt(orderData.getNum().contains(".")?orderData.getNum().substring(0,orderData.getNum().indexOf(".")):orderData.getNum());
                    for (int i = 0; i < count; i++) {
                        OrderItem orderItem = new OrderItem();
                        orderItem.setTime(orderData.getTime());
                        orderItems.add(orderItem);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.e("order num is not int");
                }
            }
        }
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

}
