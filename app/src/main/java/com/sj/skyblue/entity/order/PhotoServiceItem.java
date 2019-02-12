package com.sj.skyblue.entity.order;

import com.sj.module_lib.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunj on 2018/11/15.
 */

public class PhotoServiceItem implements Cloneable{
    private String id;
    private String serviceOrderId;//服务订单id
    private String baseOrderId;//订单id
    private String photoOrderId;//照片订单id
    private String serviceName;//服务名称
    private String picUrl;//照片地址
    private String tierPrice;//服务价格
    private String tierAddPrice;//加选服务价格
    private String isExtra;//是否新增服务 0:基础照片订单 1:加选照片订单
    private OrderPhoto basePhotoItem;//基础
    private List<OrderPhoto> photoItems = new ArrayList<>();//加印
    private List<OrderService.SizeColor> sizes;
    private List<OrderService.SizeColor> colors;
    private List<OrderService.PrintPrizesBean> printPrizes;
    private String prePrice;//服务名称

    public String getPrePrice() {
        return StringUtils.isEmpty(prePrice)?"0":prePrice;
    }

    public void setPrePrice(String prePrice) {
        this.prePrice = prePrice;
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

    public List<OrderPhoto> getPhotoItems() {
        return photoItems;
    }

    public void setPhotoItems(List<OrderPhoto> photoItems) {
        this.photoItems = photoItems;
    }

    public String getPhotoOrderId() {
        return photoOrderId;
    }

    public void setPhotoOrderId(String photoOrderId) {
        this.photoOrderId = photoOrderId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIsExtra() {
        return StringUtils.isEmpty(isExtra)?"0":isExtra;
    }

    public void setIsExtra(String isExtra) {
        this.isExtra = isExtra;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public List<OrderService.SizeColor> getSizes() {
        return sizes;
    }

    public void setSizes(List<OrderService.SizeColor> sizes) {
        this.sizes = sizes;
    }

    public List<OrderService.SizeColor> getColors() {
        return colors;
    }

    public void setColors(List<OrderService.SizeColor> colors) {
        this.colors = colors;
    }

    public List<OrderService.PrintPrizesBean> getPrintPrizes() {
        return printPrizes;
    }

    public void setPrintPrizes(List<OrderService.PrintPrizesBean> printPrizes) {
        this.printPrizes = printPrizes;
    }

    public String getTierPrice() {
        return tierPrice;
    }

    public void setTierPrice(String tierPrice) {
        this.tierPrice = tierPrice;
    }

    public String getTierAddPrice() {
        return tierAddPrice;
    }

    public void setTierAddPrice(String tierAddPrice) {
        this.tierAddPrice = tierAddPrice;
    }

    public OrderPhoto getBasePhotoItem() {
        return basePhotoItem;
    }

    public void setBasePhotoItem(OrderPhoto basePhotoItem) {
        this.basePhotoItem = basePhotoItem;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Object clone() {
        PhotoServiceItem photoServiceItem = null;
        try{
            photoServiceItem = (PhotoServiceItem)super.clone();   //浅复制
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        photoServiceItem.basePhotoItem = (OrderPhoto) basePhotoItem.clone();   //深度复制
        return photoServiceItem;
    }

    @Override
    public String toString() {
        return "PhotoServiceItem{" +
                "id='" + id + '\'' +
                ", serviceOrderId='" + serviceOrderId + '\'' +
                ", baseOrderId='" + baseOrderId + '\'' +
                ", photoOrderId='" + photoOrderId + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", isExtra='" + isExtra + '\'' +
                ", basePhotoItem=" + basePhotoItem +
                ", photoItems=" + photoItems +
                ", sizes=" + sizes +
                ", colors=" + colors +
                ", printPrizes=" + printPrizes +
                '}';
    }
}
