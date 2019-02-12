package com.sj.skyblue.entity.order;

import android.text.TextUtils;

import com.sj.module_lib.utils.StringUtils;
import com.sj.skyblue.entity.OrderCombo;
import com.sj.skyblue.entity.OrderInfoCombo;

/**
 * Created by Sunj on 2018/11/15.
 */

public class OrderInfo {


    private String employeeName;//收银员
    private String consignee;//收件人
    private String appointmentTime; //预约时间
    private String orderId;//订单id
    private String cusName;//客户名称
    private String phone;//客户手机
    private String receiveAddress;//收货地址
    private String appointmentId;//预约id
    private String paid;//预约款
    private String discount;//折扣信息，具体金额以元为单位
    private String phoneNum;//收件人手机号
    private String shopId;//当前门店id
    private String status;//状态
    private String remark;//备注信息
    private OrderInfoCombo combo;

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

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

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPaid() {
        return StringUtils.isEmpty(paid)?"0":paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }


    public String getStatus() {
        return TextUtils.isEmpty(status)?"0":status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEmployeeName() {
        return StringUtils.isEmpty(employeeName)?"":employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public OrderInfoCombo getCombo() {
        return combo;
    }

    public void setCombo(OrderInfoCombo combo) {
        this.combo = combo;
    }
}
