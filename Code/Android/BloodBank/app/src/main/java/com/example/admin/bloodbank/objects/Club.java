package com.example.admin.bloodbank.objects;

/**
 * Created by Admin on 11/01/2017.
 */

public class Club {
    private String countMember;
    private String nameClub;
    private String address;
    private String addressFacebook;

    public Club() {
    }

    public Club(String countMember, String nameClub, String address, String addressFacebook) {
        this.countMember = countMember;
        this.nameClub = nameClub;
        this.address = address;
        this.addressFacebook = addressFacebook;
    }

    public String getCountMember() {
        return countMember;
    }

    public void setCountMember(String countMember) {
        this.countMember = countMember;
    }

    public String getNameClub() {
        return nameClub;
    }

    public void setNameClub(String nameClub) {
        this.nameClub = nameClub;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressFacebook() {
        return addressFacebook;
    }

    public void setAddressFacebook(String addressFacebook) {
        this.addressFacebook = addressFacebook;
    }
}
