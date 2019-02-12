package com.sj.skyblue.event;

import com.sj.skyblue.entity.order.PhotoServiceItem;

/**
 * Created by Sunj on 2018/11/18.
 */

public class OrderAddExtEvent {

    public PhotoServiceItem item;
    public OrderAddExtEvent(PhotoServiceItem item) {
        this.item = item;
    }
}
