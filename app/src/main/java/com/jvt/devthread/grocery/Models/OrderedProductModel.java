package com.jvt.devthread.grocery.Models;

public class OrderedProductModel {
    String productName, pic, id;
    Long price;

    public OrderedProductModel() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public OrderedProductModel(String productName, String pic, String id, Long price) {
        this.productName = productName;
        this.pic = pic;
        this.id = id;
        this.price = price;
    }
}
