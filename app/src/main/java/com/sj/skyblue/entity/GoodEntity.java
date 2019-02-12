package com.sj.skyblue.entity;

import com.sj.skyblue.entity.base.ResEntity;

/**
 * Created by Sunj on 2018/11/11.
 */

public class GoodEntity extends ResEntity{


    /**
     * goodPicture :
     * goodId : 922337049268789555187c826ebbf9446c1a6b0fed1d4c6edb0
     * shopId : 999998458764558850
     * goodPrice : 228
     * goodNum : 1
     */

    private String goodPicture;
    private String goodId;
    private String shopId;
    private String goodPrice;
    private String goodNum;
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getGoodPicture() {
        return goodPicture;
    }

    public void setGoodPicture(String goodPicture) {
        this.goodPicture = goodPicture;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(String goodNum) {
        this.goodNum = goodNum;
    }
}
