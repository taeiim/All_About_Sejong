package com.example.parktaeim.all_about_sejong;

/**
 * Created by parktaeim on 2018. 2. 2..
 */

public class AllCenterItem {
    String centerName;
    String centerAddress;
    Boolean isBus;
    int centerType;

    public AllCenterItem(String centerName, String centerAddress, Boolean isBus, int centerType) {
        this.centerName = centerName;
        this.centerAddress = centerAddress;
        this.isBus = isBus;
        this.centerType = centerType;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterAddress() {
        return centerAddress;
    }

    public void setCenterAddress(String centerAddress) {
        this.centerAddress = centerAddress;
    }

    public Boolean getBus() {
        return isBus;
    }

    public void setBus(Boolean bus) {
        isBus = bus;
    }

    public int getCenterType() {
        return centerType;
    }

    public void setCenterType(int centerType) {
        this.centerType = centerType;
    }
}
