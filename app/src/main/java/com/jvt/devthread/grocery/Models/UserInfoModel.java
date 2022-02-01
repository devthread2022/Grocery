package com.jvt.devthread.grocery.Models;

public class UserInfoModel {
    String uid, name, email, mobile, username, address, pic, enteredReferral, userReferral, password;

    public UserInfoModel() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getEnteredReferral() {
        return enteredReferral;
    }

    public void setEnteredReferral(String enteredReferral) {
        this.enteredReferral = enteredReferral;
    }

    public String getUserReferral() {
        return userReferral;
    }

    public void setUserReferral(String userReferral) {
        this.userReferral = userReferral;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfoModel(String uid, String name, String email, String mobile, String username, String address, String pic, String enteredReferral, String userReferral, String password) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.username = username;
        this.address = address;
        this.pic = pic;
        this.enteredReferral = enteredReferral;
        this.userReferral = userReferral;
        this.password = password;
    }
}
