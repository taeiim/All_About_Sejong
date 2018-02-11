package com.example.parktaeim.all_about_sejong;

/**
 * Created by parktaeim on 2018. 2. 11..
 */

public class ToiletItem {
    String toilet_openTime;
    String toilet_address;
    double toilet_latitude;
    double toilet_longitude;
    String toilet_tellNum;
    String toilet_name;

    public ToiletItem(String toilet_openTime, String toilet_address, double toilet_latitude, double toilet_longitude, String toilet_tellNum, String toilet_name) {
        this.toilet_openTime = toilet_openTime;
        this.toilet_address = toilet_address;
        this.toilet_latitude = toilet_latitude;
        this.toilet_longitude = toilet_longitude;
        this.toilet_tellNum = toilet_tellNum;
        this.toilet_name = toilet_name;
    }

    public String getToilet_openTime() {
        return toilet_openTime;
    }

    public void setToilet_openTime(String toilet_openTime) {
        this.toilet_openTime = toilet_openTime;
    }

    public String getToilet_address() {
        return toilet_address;
    }

    public void setToilet_address(String toilet_address) {
        this.toilet_address = toilet_address;
    }

    public double getToilet_latitude() {
        return toilet_latitude;
    }

    public void setToilet_latitude(double toilet_latitude) {
        this.toilet_latitude = toilet_latitude;
    }

    public double getToilet_longitude() {
        return toilet_longitude;
    }

    public void setToilet_longitude(double toilet_longitude) {
        this.toilet_longitude = toilet_longitude;
    }

    public String getToilet_tellNum() {
        return toilet_tellNum;
    }

    public void setToilet_tellNum(String toilet_tellNum) {
        this.toilet_tellNum = toilet_tellNum;
    }

    public String getToilet_name() {
        return toilet_name;
    }

    public void setToilet_name(String toilet_name) {
        this.toilet_name = toilet_name;
    }

    @Override
    public String toString() {
        return "ToiletItem{" +
                "toilet_openTime='" + toilet_openTime + '\'' +
                ", toilet_address='" + toilet_address + '\'' +
                ", toilet_latitude=" + toilet_latitude +
                ", toilet_longitude=" + toilet_longitude +
                ", toilet_tellNum='" + toilet_tellNum + '\'' +
                ", toilet_name='" + toilet_name + '\'' +
                '}';
    }
}
