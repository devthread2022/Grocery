package com.jvt.devthread.grocery.Models;

public class WalletModel {
    double walletBalance;

    public WalletModel() {
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public WalletModel(double walletBalance) {
        this.walletBalance = walletBalance;
    }
}
