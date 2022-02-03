package com.jvt.devthread.grocery.Models;

public class OrderListModel {
    String orderId, date;
    Long amount;

    public OrderListModel() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public OrderListModel(String orderId, String date, Long amount) {
        this.orderId = orderId;
        this.date = date;
        this.amount = amount;
    }
}
