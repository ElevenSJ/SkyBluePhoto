package com.sj.skyblue.entity;

import com.sj.module_lib.utils.StringUtils;

import java.io.Serializable;

/**
 * Created by Sunj on 2018/11/4.
 */

public class AppointmentEntity implements Serializable{


    /**
     * appointmentTime : 2018-11-10 13:28:53
     * phone : 17688912200
     * id : DD1539826907301AJAX
     * serviceName : test1
     * status : 1
     */

    private String appointmentTime;
    private String phone;
    private String id;
    private String serviceName;
    private String status = "6";//默认是新建订单
    private boolean isSelected = false;

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getStatus() {
        return StringUtils.isEmpty(status)?"6":status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
