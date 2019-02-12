package com.sj.skyblue.entity.order;

import com.sj.module_lib.utils.StringUtils;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.order.base.OrderBase;

import java.util.List;

/**
 * Created by Sunj on 2018/11/15.
 */

public class OrderPhoto extends OrderBase implements Cloneable{
    private String serviceName;//服务名称
    private String serviceOrderId;//服务订单id
    private String baseOrderId;//订单id
    private String photoOrderId;//照片订单id
    private String size;
    private String color;
    private String orderId;
    private String orderNum;//交易流水号
    private String price;
    private String id;
    private int count=1;
    private int peopleCount=1;
    private int paidCount;
    private int refundCount;
    private String status;//支付状态
    private String payChannel;//支付渠道
    private String payChannelName;//支付渠道名称
    private List<String> sizes;


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getStatus() {
        return StringUtils.isEmpty(status)? Constants.PAY_STATUS_NOT:status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getPayChannelName() {
        return payChannelName;
    }

    public void setPayChannelName(String payChannelName) {
        this.payChannelName = payChannelName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(String serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public String getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(String baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public String getPhotoOrderId() {
        return photoOrderId;
    }

    public void setPhotoOrderId(String photoOrderId) {
        this.photoOrderId = photoOrderId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public int getPaidCount() {
        return paidCount;
    }

    public void setPaidCount(int paidCount) {
        this.paidCount = paidCount;
    }

    public int getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(int refundCount) {
        this.refundCount = refundCount;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    @Override
    public Object clone() {
        OrderPhoto orderPhoto = null;
        try{
            orderPhoto = (OrderPhoto)super.clone();   //浅复制
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return orderPhoto;
    }

    @Override
    public String toString() {
        return "OrderPhoto{" +
                "serviceName='" + serviceName + '\'' +
                ", serviceOrderId='" + serviceOrderId + '\'' +
                ", baseOrderId='" + baseOrderId + '\'' +
                ", photoOrderId='" + photoOrderId + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", price='" + price + '\'' +
                ", id='" + id + '\'' +
                ", count=" + count +
                ", peopleCount=" + peopleCount +
                ", status='" + status + '\'' +
                ", payChannel='" + payChannel + '\'' +
                ", payChannelName='" + payChannelName + '\'' +
                '}';
    }
}
