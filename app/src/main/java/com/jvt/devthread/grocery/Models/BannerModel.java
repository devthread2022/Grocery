package com.jvt.devthread.grocery.Models;

public class BannerModel {
    String id, banner;

    public BannerModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public BannerModel(String id, String banner) {
        this.id = id;
        this.banner = banner;
    }
}
