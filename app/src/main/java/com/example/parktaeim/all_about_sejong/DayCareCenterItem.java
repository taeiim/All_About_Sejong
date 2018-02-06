package com.example.parktaeim.all_about_sejong;

/**
 * Created by parktaeim on 2018. 2. 6..
 */

public class DayCareCenterItem {
    public String fax;
    public String name;
    public String type;
    public Boolean isCar;
    public String tellNum;
    public int cctvCount;
    public String address;
    public int studentCount;
    public int teacherCount;
    public int playgroundCount;
    public int roomCount;

    public DayCareCenterItem(String fax, String name, String type, Boolean isCar, String tellNum, int cctvCount, String address, int studentCount, int teacherCount, int playgroundCount, int roomCount) {
        this.fax = fax;
        this.name = name;
        this.type = type;
        this.isCar = isCar;
        this.tellNum = tellNum;
        this.cctvCount = cctvCount;
        this.address = address;
        this.studentCount = studentCount;
        this.teacherCount = teacherCount;
        this.playgroundCount = playgroundCount;
        this.roomCount = roomCount;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getCar() {
        return isCar;
    }

    public void setCar(Boolean car) {
        isCar = car;
    }

    public String getTellNum() {
        return tellNum;
    }

    public void setTellNum(String tellNum) {
        this.tellNum = tellNum;
    }

    public int getCctvCount() {
        return cctvCount;
    }

    public void setCctvCount(int cctvCount) {
        this.cctvCount = cctvCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getTeacherCount() {
        return teacherCount;
    }

    public void setTeacherCount(int teacherCount) {
        this.teacherCount = teacherCount;
    }

    public int getPlaygroundCount() {
        return playgroundCount;
    }

    public void setPlaygroundCount(int playgroundCount) {
        this.playgroundCount = playgroundCount;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    @Override
    public String toString() {
        return "DayCareCenterItem{" +
                "fax='" + fax + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", isCar=" + isCar +
                ", tellNum='" + tellNum + '\'' +
                ", cctvCount=" + cctvCount +
                ", address='" + address + '\'' +
                ", studentCount=" + studentCount +
                ", teacherCount=" + teacherCount +
                ", playgroundCount=" + playgroundCount +
                ", roomCount=" + roomCount +
                '}';
    }
}
