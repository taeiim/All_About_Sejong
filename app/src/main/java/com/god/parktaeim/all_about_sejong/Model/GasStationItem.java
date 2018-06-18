package com.god.parktaeim.all_about_sejong.Model;

/**
 * Created by parktaeim on 2018. 3. 11..
 */

public class GasStationItem {
    private String id;
    private String name;
    private String address;
    private String price;
    private String type;
    private int rank;

    public GasStationItem() {
    }

    public GasStationItem(int rank, String id, String name, String address, String price, String type) {
        this.rank = rank;
        this.id = id;
        this.name = name;
        this.address = address;
        this.price = price;
        this.type = type;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
