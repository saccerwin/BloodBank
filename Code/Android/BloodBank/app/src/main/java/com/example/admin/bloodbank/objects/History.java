package com.example.admin.bloodbank.objects;

/**
 * Created by saccerwin on 22/01/2017.
 */

public class History  {
    private String id;
    private String date;
    private String addressHospital;
    private String contentHistory;

    public History(String date, String address_hospital, String contentHistory) {
        this.date = date;
        this.addressHospital = address_hospital;
        this.contentHistory = contentHistory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddressHospital() {
        return addressHospital;
    }

    public void setAddressHospital(String addressHospital) {
        this.addressHospital = addressHospital;
    }

    public String getContentHistory() {
        return contentHistory;
    }

    public void setContentHistory(String contentHistory) {
        this.contentHistory = contentHistory;
    }
}
