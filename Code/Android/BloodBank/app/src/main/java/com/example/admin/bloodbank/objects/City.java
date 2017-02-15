package com.example.admin.bloodbank.objects;

/**
 * Created by Admin on 15/02/2017.
 */

public class City {
    private String idCity;
    private String nameCity;

    public City() {
    }

    public City(String idCity, String nameCity) {
        this.idCity = idCity;
        this.nameCity = nameCity;
    }

    public String getIdCity() {
        return idCity;
    }

    public void setIdCity(String idCity) {
        this.idCity = idCity;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }
}
