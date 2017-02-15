package com.example.admin.bloodbank.objects;

/**
 * Created by Admin on 15/02/2017.
 */

public class District {
    private String idDistict;
    private String nameDistict;

    public District() {
    }

    public District(String idDistict, String nameDistict) {
        this.idDistict = idDistict;
        this.nameDistict = nameDistict;
    }

    public String getIdDistict() {
        return idDistict;
    }

    public void setIdDistict(String idDistict) {
        this.idDistict = idDistict;
    }

    public String getNameDistict() {
        return nameDistict;
    }

    public void setNameDistict(String nameDistict) {
        this.nameDistict = nameDistict;
    }
}
