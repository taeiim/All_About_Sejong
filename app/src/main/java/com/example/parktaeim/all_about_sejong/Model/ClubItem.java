package com.example.parktaeim.all_about_sejong.Model;

/**
 * Created by parktaeim on 2018. 3. 6..
 */

public class ClubItem {
    public String name;
    public String tellNum;
    public String regularMeeting;
    public String clubType;
    public String content;
    public String membershipFee;
    public String memberCnt;
    public String businessName;
    public String category;
    public String cafeUrl;
    public double latitude;
    public double longitude;

    public ClubItem(String name, String tellNum, String regularMeeting, String clubType, String content, String membershipFee, String memberCnt, String businessName, String category, String cafeUrl) {
        this.name = name;
        this.tellNum = tellNum;
        this.regularMeeting = regularMeeting;
        this.clubType = clubType;
        this.content = content;
        this.membershipFee = membershipFee;
        this.memberCnt = memberCnt;
        this.businessName = businessName;
        this.category = category;
        this.cafeUrl = cafeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTellNum() {
        return tellNum;
    }

    public void setTellNum(String tellNum) {
        this.tellNum = tellNum;
    }

    public String getRegularMeeting() {
        return regularMeeting;
    }

    public void setRegularMeeting(String regularMeeting) {
        this.regularMeeting = regularMeeting;
    }

    public String getClubType() {
        return clubType;
    }

    public void setClubType(String clubType) {
        this.clubType = clubType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMembershipFee() {
        return membershipFee;
    }

    public void setMembershipFee(String membershipFee) {
        this.membershipFee = membershipFee;
    }

    public String getMemberCnt() {
        return memberCnt;
    }

    public void setMemberCnt(String memberCnt) {
        this.memberCnt = memberCnt;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCafeUrl() {
        return cafeUrl;
    }

    public void setCafeUrl(String cafeUrl) {
        this.cafeUrl = cafeUrl;
    }

}
