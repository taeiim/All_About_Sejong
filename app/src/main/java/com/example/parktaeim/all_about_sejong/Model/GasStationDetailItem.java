package com.example.parktaeim.all_about_sejong.Model;

/**
 * Created by parktaeim on 2018. 3. 17..
 */

public class GasStationDetailItem {
    private String brand;
    private String name;
    private String address_new;
    private String tellNum;
    private String type;
    private boolean isMaint;
    private boolean isCarWash;
    private boolean isConvenience;
    private double latitude;
    private double longitude;

    //휘발유
    private String B027;
    private int B027_price;
    private String B027_date;

    //경유
    private String D047;
    private int D047_price;
    private String D047_date;

    //고급휘발유
    private String B034;
    private int B034_price;
    private String B034_date;

    //실내등유
    private String C004;
    private int C004_price;
    private String C004_date;

    //자동차부탄
    private String K015;
    private int K015_price;
    private String K015_date;

    public GasStationDetailItem(String name, String type) {
        this.name = name;
        this.type = type;
    }

    //기본정보+휘발유,경유
    public GasStationDetailItem(String brand, String name, String address_new, String tellNum, String type, Boolean isMaint, Boolean isCarWash, Boolean isConvenience, double latitude, double longitude, String b027, int b027_price, String b027_date, String d047, int d047_price, String d047_date) {
        this.brand = brand;
        this.name = name;
        this.address_new = address_new;
        this.tellNum = tellNum;
        this.type = type;
        this.isMaint = isMaint;
        this.isCarWash = isCarWash;
        this.isConvenience = isConvenience;
        this.latitude = latitude;
        this.longitude = longitude;
        B027 = b027;
        B027_price = b027_price;
        B027_date = b027_date;
        D047 = d047;
        D047_price = d047_price;
        D047_date = d047_date;
    }

    //기본정보+기름전부
    public GasStationDetailItem(String brand, String name, String address_new, String tellNum, String type, Boolean isMaint, Boolean isCarWash, Boolean isConvenience, double latitude, double longitude, String b027, int b027_price, String b027_date, String d047, int d047_price, String d047_date, String b034, int b034_price, String b034_date, String c004, int c004_price, String c004_date, String k015, int k015_price, String k015_date) {
        this.brand = brand;
        this.name = name;
        this.address_new = address_new;
        this.tellNum = tellNum;
        this.type = type;
        this.isMaint = isMaint;
        this.isCarWash = isCarWash;
        this.isConvenience = isConvenience;
        this.latitude = latitude;
        this.longitude = longitude;
        B027 = b027;
        B027_price = b027_price;
        B027_date = b027_date;
        D047 = d047;
        D047_price = d047_price;
        D047_date = d047_date;
        B034 = b034;
        B034_price = b034_price;
        B034_date = b034_date;
        C004 = c004;
        C004_price = c004_price;
        C004_date = c004_date;
        K015 = k015;
        K015_price = k015_price;
        K015_date = k015_date;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress_new() {
        return address_new;
    }

    public void setAddress_new(String address_new) {
        this.address_new = address_new;
    }

    public String getTellNum() {
        return tellNum;
    }

    public void setTellNum(String tellNum) {
        this.tellNum = tellNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getMaint() {
        return isMaint;
    }

    public void setMaint(Boolean maint) {
        isMaint = maint;
    }

    public Boolean getCarWash() {
        return isCarWash;
    }

    public void setCarWash(Boolean carWash) {
        isCarWash = carWash;
    }

    public Boolean getConvenience() {
        return isConvenience;
    }

    public void setConvenience(Boolean convenience) {
        isConvenience = convenience;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getB027() {
        return B027;
    }

    public void setB027(String b027) {
        B027 = b027;
    }

    public int getB027_price() {
        return B027_price;
    }

    public void setB027_price(int b027_price) {
        B027_price = b027_price;
    }

    public String getB027_date() {
        return B027_date;
    }

    public void setB027_date(String b027_date) {
        B027_date = b027_date;
    }

    public String getD047() {
        return D047;
    }

    public void setD047(String d047) {
        D047 = d047;
    }

    public int getD047_price() {
        return D047_price;
    }

    public void setD047_price(int d047_price) {
        D047_price = d047_price;
    }

    public String getD047_date() {
        return D047_date;
    }

    public void setD047_date(String d047_date) {
        D047_date = d047_date;
    }

    public String getB034() {
        return B034;
    }

    public void setB034(String b034) {
        B034 = b034;
    }

    public int getB034_price() {
        return B034_price;
    }

    public void setB034_price(int b034_price) {
        B034_price = b034_price;
    }

    public String getB034_date() {
        return B034_date;
    }

    public void setB034_date(String b034_date) {
        B034_date = b034_date;
    }

    public String getC004() {
        return C004;
    }

    public void setC004(String c004) {
        C004 = c004;
    }

    public int getC004_price() {
        return C004_price;
    }

    public void setC004_price(int c004_price) {
        C004_price = c004_price;
    }

    public String getC004_date() {
        return C004_date;
    }

    public void setC004_date(String c004_date) {
        C004_date = c004_date;
    }

    public String getK015() {
        return K015;
    }

    public void setK015(String k015) {
        K015 = k015;
    }

    public int getK015_price() {
        return K015_price;
    }

    public void setK015_price(int k015_price) {
        K015_price = k015_price;
    }

    public String getK015_date() {
        return K015_date;
    }

    public void setK015_date(String k015_date) {
        K015_date = k015_date;
    }
}
