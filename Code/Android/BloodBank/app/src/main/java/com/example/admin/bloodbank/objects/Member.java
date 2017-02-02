package com.example.admin.bloodbank.objects;

/**
 * Created by Admin on 11/01/2017.
 */

public class Member {
    private String name;
    private String avatar;
    private String position;
    private String nameClub;
    private String phone;

    public Member() {
    }

    public Member(String name, String avatar, String position, String nameClub, String phone) {
        this.name = name;
        this.avatar = avatar;
        this.position = position;
        this.nameClub = nameClub;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNameClub() {
        return nameClub;
    }

    public void setNameClub(String nameClub) {
        this.nameClub = nameClub;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
