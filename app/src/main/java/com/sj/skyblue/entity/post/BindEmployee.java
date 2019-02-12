package com.sj.skyblue.entity.post;

/**
 * Created by Sunj on 2018/11/27.
 */

public class BindEmployee {


    /**
     * token : 9223370493902909103126a4486fafb40579e171e82f8582b69
     * orderId : DD1542944174048UaUn
     * employeeId : 9223370495944777720e899021d548a41f2b1ad6cefad418cc9
     */

    private String token;
    private String orderId;
    private String employeeId;

    public BindEmployee(String tokenId, String orderId, String employeeId) {
        this.token = tokenId;
        this.orderId = orderId;
        this.employeeId = employeeId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
