package com.sj.skyblue.entity;

import java.io.Serializable;

/**
 * Created by Sunj on 2018/12/17.
 */

public class PayMethod implements Serializable{


    private static final long serialVersionUID = 5211754640148693056L;
    /**
     * code : 01
     * createTime : 2018-12-14 11:53:01
     * name : 支付宝
     * id : 9223370492095194767055833bbbd2b4cb591f46d0a3f91127f
     * type : 支付方式
     */

    private String code;
    private String createTime;
    private String name;
    private String id;
    private String type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
