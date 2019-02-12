package com.sj.skyblue.entity.post;

/**
 * Created by Sunj on 2018/11/18.
 */

public class AddProductBean {

    /**
     * id : 92233704947461935614c144b2363b14cc8b607cd09621d3d2a
     * type : 在售商品
     * name : 木讷相框（6寸
     * price : 120
     * count : 1
     */

    private String id;
    private String type;
    private String name;
    private String price;
    private String count;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
