package com.sj.skyblue.entity;

import java.io.Serializable;

/**
 * Created by Sunj on 2018/11/1.
 */

public class UserInfoEntity implements Serializable{

    private static final long serialVersionUID = -5739168184177393581L;
    /**
     * tokenid : 92233704958086395737a28f361c56d400a8021156b197b6d0e
     * roleId : 9223370496651312234dbb768becbff4164ab84bc3077252d83
     * index : 20181101jM5w
     * salary : 1000
     * userName : 孙杰
     * token : 9223370495820482117ab36eef498c54fe9b6dba7d64c8fec0b
     * entryTime : 2018-10-31
     * createTime : 2018-10-29 22:33:05
     * name : 15605198042
     * id : 9223370495944777720e899021d548a41f2b1ad6cefad418cc9
     * shopId : 999998460487509760
     * status : 2
     */

    private String tokenid;
    private String roleId;
    private String index;
    private String salary;
    private String userName;
    private String token;
    private String entryTime;
    private String createTime;
    private String name;
    private String id;
    private String shopId;
    private String status;
    private String headPortrait;

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
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

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    @Override
    public String toString() {
        return "UserInfoEntity{" +
                "tokenid='" + tokenid + '\'' +
                ", roleId='" + roleId + '\'' +
                ", index='" + index + '\'' +
                ", salary='" + salary + '\'' +
                ", userName='" + userName + '\'' +
                ", token='" + token + '\'' +
                ", entryTime='" + entryTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", shopId='" + shopId + '\'' +
                ", status='" + status + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                '}';
    }
}
