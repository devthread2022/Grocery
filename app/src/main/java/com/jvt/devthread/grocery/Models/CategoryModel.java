package com.jvt.devthread.grocery.Models;

public class CategoryModel {
    String id, name, node, pic;

    public CategoryModel() {
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

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public CategoryModel(String id, String name, String node, String pic) {
        this.id = id;
        this.name = name;
        this.node = node;
        this.pic = pic;
    }
}
