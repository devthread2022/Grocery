package com.jvt.devthread.grocery.Models;

public class ProductListModel {
    String id, name, pic, stockStatus;
    Long price;

    public ProductListModel() {
    }

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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public ProductListModel(String id, String name, String pic, String stockStatus, Long price) {
        this.id = id;
        this.name = name;
        this.pic = pic;
        this.stockStatus = stockStatus;
        this.price = price;
    }
}
