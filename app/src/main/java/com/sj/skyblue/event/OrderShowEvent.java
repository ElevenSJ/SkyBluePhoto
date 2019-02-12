package com.sj.skyblue.event;

import com.sj.skyblue.entity.order.base.OrderBase;
import com.sj.skyblue.entity.order.base.OrderServiceBase;

import java.util.List;

/**
 * Created by Sunj on 2018/11/18.
 */

public class OrderShowEvent {

    List<OrderBase> orderBaseList;

    double totalPrice = 0d;
    double paidPrice = 0d;
    double comboPrice = 0d;
    double discount = 0d;
    double needPayPrice = 0d;

    private OrderShowEvent(Builder builder) {
        orderBaseList = builder.orderBaseList;
        totalPrice = builder.totalPrice;
        paidPrice = builder.paidPrice;
        comboPrice = builder.comboPrice;
        discount = builder.discount;
        needPayPrice = builder.needPayPrice;
    }

    public List<OrderBase> getOrderBaseList() {
        return orderBaseList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getPaidPrice() {
        return paidPrice;
    }

    public double getComboPrice() {
        return comboPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public double getNeedPayPrice() {
        return needPayPrice;
    }

    public static final class Builder {
        private List<OrderBase> orderBaseList;
        private double totalPrice;
        private double paidPrice;
        private double comboPrice;
        private double discount;
        private double needPayPrice;

        public Builder() {
        }

        public Builder orderBaseList(List<OrderBase> val) {
            orderBaseList = val;
            return this;
        }

        public Builder totalPrice(double val) {
            totalPrice = val;
            return this;
        }

        public Builder paidPrice(double val) {
            paidPrice = val;
            return this;
        }

        public Builder comboPrice(double val) {
            comboPrice = val;
            return this;
        }

        public Builder discount(double val) {
            discount = val;
            return this;
        }

        public Builder needPayPrice(double val) {
            needPayPrice = val;
            return this;
        }

        public OrderShowEvent build() {
            return new OrderShowEvent(this);
        }
    }
}
