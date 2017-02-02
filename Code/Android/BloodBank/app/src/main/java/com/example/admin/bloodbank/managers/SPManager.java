package com.example.admin.bloodbank.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


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

    public void clear() {
        sp.edit().clear().apply();
    }
}
