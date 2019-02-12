package com.sj.skyblue.entity.base;

import java.util.List;

/**
 * Created by Sunj on 2018/7/8.
 */

public class BaseDataList<E> {

    List<E> datalist;
    String count;
    String nextFirstIndex;

    public List<E> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<E> datalist) {
        this.datalist = datalist;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNextFirstIndex() {
        return nextFirstIndex;
    }

    public void setNextFirstIndex(String nextFirstIndex) {
        this.nextFirstIndex = nextFirstIndex;
    }
}
