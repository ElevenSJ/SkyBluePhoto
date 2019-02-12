package com.sj.skyblue.entity;

import java.util.List;

/**
 * Created by Sunj on 2018/11/13.
 */

public class EmployeesEntity {

    private List<CashierInfo> stages;
    private List<EmployeeBase> shops;

    public List<CashierInfo> getStages() {
        return stages;
    }

    public void setStages(List<CashierInfo> stages) {
        this.stages = stages;
    }

    public List<EmployeeBase> getShops() {
        return shops;
    }

    public void setShops(List<EmployeeBase> shops) {
        this.shops = shops;
    }
}
