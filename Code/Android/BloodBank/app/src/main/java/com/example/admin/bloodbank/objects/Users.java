package com.example.admin.bloodbank.objects;

import java.util.UUID;

/**
 * Created by Admin on 09/02/2017.
 */

public class Users {

    private String id;
    private String id_club;
    private String id_distict;
    private String id_discuss;
    private String permission;
    private String email;
    private String password;
    private String fullName;
    private String dateOfBirth;
    private String gender;
    private String phone;
    private String picture_avatar;
    private int quality_donation;
    private String type_blood;
    private boolean isCheckDonation;

    public Users(String id_club, String id_distict, String id_discuss, String permission, String email, String password, String fullName, String dateOfBirth, String gender, String phone, String picture_avatar, int quality_donation, String type_blood, boolean isCheckDonation) {
        this.id = UUID.randomUUID().toString();
        this.id_club = id_club;
        this.id_distict = id_distict;
        this.id_discuss = id_discuss;
        this.permission = permission;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phone = phone;
        this.picture_avatar = picture_avatar;
        this.quality_donation = quality_donation;
        this.type_blood = type_blood;
        this.isCheckDonation = isCheckDonation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_distict() {
        return id_distict;
    }

    public void setId_distict(String id_distict) {
        this.id_distict = id_distict;
    }

    public String getId_club() {
        return id_club;
    }

    public void setId_club(String id_club) {
        this.id_club = id_club;
    }

    public String getId_discuss() {
        return id_discuss;
    }

    public void setId_discuss(String id_discuss) {
        this.id_discuss = id_discuss;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPicture_avatar() {
        return picture_avatar;
    }

    public void setPicture_avatar(String picture_avatar) {
        this.picture_avatar = picture_avatar;
    }

    public int getQuality_donation() {
        return quality_donation;
    }

    public void setQuality_donation(int quality_donation) {
        this.quality_donation = quality_donation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getType_blood() {
        return type_blood;
    }

    public void setType_blood(String type_blood) {
        this.type_blood = type_blood;
    }

    public boolean isCheckDonation() {
        return isCheckDonation;
    }

    public void setCheckDonation(boolean checkDonation) {
        isCheckDonation = checkDonation;
    }



}
