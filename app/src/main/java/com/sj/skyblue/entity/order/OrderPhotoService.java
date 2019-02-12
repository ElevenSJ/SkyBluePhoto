package com.sj.skyblue.entity.order;

import com.sj.module_lib.utils.StringUtils;
import com.sj.skyblue.entity.order.base.OrderServiceBase;

import java.util.List;

/**
 * Created by Sunj on 2018/11/15.
 */

public class OrderPhotoService extends OrderServiceBase {

    private String baseColor;//选择的基本颜色
    private String prePrice;//服务名称
    private String tierPrice;//服务价格
    private String tierAddPrice;//加选服务价格
    private String isExtra;//0基础服务，1还是加选服务
    private String serviceOrderId;//服务订单id
    private String serviceId;//服务id
    private String baseOrderId;//订单id
    private String name;//服务名称
    private String type;
    private List<PhotoServiceItem> photoServiceItems;

    public String getPrePrice() {
        return StringUtils.isEmpty(prePrice)?"0":prePrice;
    }

    public void setPrePrice(String prePrice) {
        this.prePrice = prePrice;
    }

    public String getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(String baseColor) {
        this.baseColor = baseColor;
    }

    public String getTierPrice() {
        return tierPrice;
    }

    public void setTierPrice(String tierPrice) {
        this.tierPrice = tierPrice;
    }

    public String getIsExtra() {
        return isExtra;
    }

    public void setIsExtra(String isExtra) {
        this.isExtra = isExtra;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<PhotoServiceItem> getPhotoServiceItems() {
        return photoServiceItems;
    }

    public void setPhotoServiceItems(List<PhotoServiceItem> photoServiceItems) {
        this.photoServiceItems = photoServiceItems;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTierAddPrice() {
        return tierAddPrice;
    }

    public void setTierAddPrice(String tierAddPrice) {
        this.tierAddPrice = tierAddPrice;
    }

    @Override
    public String toString() {
        return "OrderPhotoService{" +
                "baseColor='" + baseColor + '\'' +
                ", tierPrice='" + tierPrice + '\'' +
                ", tierAddPrice='" + tierAddPrice + '\'' +
                ", isExtra='" + isExtra + '\'' +
                ", serviceOrderId='" + serviceOrderId + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", baseOrderId='" + baseOrderId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", photoServiceItems=" + photoServiceItems +
                '}';
    }
}
