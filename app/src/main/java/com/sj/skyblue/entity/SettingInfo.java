package com.sj.skyblue.entity;

/**
 * Created by Sunj on 2018/12/27.
 */

public class SettingInfo {
    int id;
    String name;
    boolean isSelected = false;

    public SettingInfo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
