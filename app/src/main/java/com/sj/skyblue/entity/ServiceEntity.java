package com.sj.skyblue.entity;

import com.sj.skyblue.entity.base.ResEntity;

/**
 * Created by Sunj on 2018/11/13.
 */

public class ServiceEntity extends ResEntity{

    /**
     * cost : 100
     * timeType : 7天
     * type : 拍摄商品
     * goodPrice :
     * printA4Photos : 20
     * tierAddPrice : 180
     * printPhotos : 10
     * name : 入学照
     * printTypePhotos : 20
     * id : 922337049473718265352d2fdf575df4bcd80a281f06165bbb7
     * properties : 普通服务
     * remarks :
     * tierPrice : 280
     */

    private String timeType;
    private String goodPrice;
    private String printA4Photos;
    private String tierAddPrice;
    private String printPhotos;
    private String printTypePhotos;
    private String properties;
    private String remarks;
    private String tierPrice;


    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }


    public String getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getPrintA4Photos() {
        return printA4Photos;
    }

    public void setPrintA4Photos(String printA4Photos) {
        this.printA4Photos = printA4Photos;
    }

    public String getTierAddPrice() {
        return tierAddPrice;
    }

    public void setTierAddPrice(String tierAddPrice) {
        this.tierAddPrice = tierAddPrice;
    }

    public String getPrintPhotos() {
        return printPhotos;
    }

    public void setPrintPhotos(String printPhotos) {
        this.printPhotos = printPhotos;
    }


    public String getPrintTypePhotos() {
        return printTypePhotos;
    }

    public void setPrintTypePhotos(String printTypePhotos) {
        this.printTypePhotos = printTypePhotos;
    }


    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTierPrice() {
        return tierPrice;
    }

    public void setTierPrice(String tierPrice) {
        this.tierPrice = tierPrice;
    }
}
