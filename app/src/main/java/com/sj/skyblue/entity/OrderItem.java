package com.sj.skyblue.entity;

import java.io.Serializable;

/**
 * Created by Sunj on 2018/12/1.
 */

public class OrderItem implements Serializable {

    private static final long serialVersionUID = 371925850179120312L;
    /**
     * appointmentTime : 2018-11-28 11:19:35
     * orderId : DD1543202375585fzlB
     * phone : 15208216231
     * cusName : 孙**
     * appointmentId : YY1542768553131gztl
     * serviceName : Naive Face
     * appStatus : 0
     * status : 1
     */
    private String time;
    private String appointmentTime;
    private String orderId;
    private String phone;
    private String cusName;
    private String appointmentId;
    private String serviceName;
    private String appStatus;
    private String status;
    private String payTime;
    private boolean isSelected = false;

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
}
