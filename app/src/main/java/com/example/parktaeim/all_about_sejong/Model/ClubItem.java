package com.example.parktaeim.all_about_sejong.Model;

/**
 * Created by parktaeim on 2018. 3. 6..
 */

public class ClubItem {
    public String name;
    public int peopleNum;

    public ClubItem(String name, int peopleNum) {
        this.name = name;
        this.peopleNum = peopleNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }
}
