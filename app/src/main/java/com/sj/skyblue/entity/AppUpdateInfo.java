package com.sj.skyblue.entity;

/**
 * Created by Sunj on 2018/12/29.
 */

public class AppUpdateInfo {


    /**
     * name : 天真蓝收银终端1.0.7.7
     * updateTime : 2018-12-24 00:12
     * id : 922337049097655117306925e18b5324b4b9e356b27a0bccc33
     * version : 1.0.7
     * platform : Android
     * url : https://naiveblue.app-service-node.com/static/apk/comsjskyblue_1.0.7_fbd275f4-d4b8-53bd-b1f1-caf6e4dc4556.apk
     */

    private String name;
    private String updateTime;
    private String id;
    private String version;
    private String platform;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
