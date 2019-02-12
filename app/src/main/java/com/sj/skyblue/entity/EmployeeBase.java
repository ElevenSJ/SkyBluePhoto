package com.sj.skyblue.entity;

import com.sj.module_lib.utils.StringUtils;

/**
 * Created by Sunj on 2018/11/30.
 */

public class EmployeeBase {
    private String id;
    private String name;
    private String headPortrait;
    private String role;//角色
    private boolean isSelected = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getRole() {
        return this instanceof CashierInfo ? "0" : "1";
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
