package com.example.admin.bloodbank.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.admin.bloodbank.contraints.Contraint;


public class SPManager {
    private SharedPreferences sp;
    private SPManager(Context context) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static synchronized SPManager getInstance(Context context) {
        return new SPManager(context);
    }

    public SharedPreferences getSharedPreferences() {
        return sp;
    }

    public String getDecentralization () {
        return sp.getString(Contraint.CHECK_LOGIN, null );
    }

    public void setDecentralization(String decentralization, String text) {
        if(text.equals(Contraint.DECENTRALIZATION_USER)) {
            sp.edit().putString(Contraint.CHECK_LOGIN, Contraint.DECENTRALIZATION_USER).apply();
        }
        if(text.equals(Contraint.DECENTRALIZATION_MEMBER)) {
            sp.edit().putString(Contraint.CHECK_LOGIN, Contraint.DECENTRALIZATION_MEMBER).apply();
        }
        if(text.equals(Contraint.DECENTRALIZATION_ADMIN)) {
            sp.edit().putString(Contraint.CHECK_LOGIN, Contraint.DECENTRALIZATION_ADMIN).apply();
        }
    }

    public void clear() {
        sp.edit().clear().apply();
    }
}
