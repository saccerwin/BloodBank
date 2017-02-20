package com.example.admin.bloodbank.applications;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Admin on 10/01/2017.
 */

public class Application  extends android.app.Application {
    public void onCreate() {
        super.onCreate();
            Realm.init(this);
            Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                    .name("BloodBank")
                    .schemaVersion(0)
                    .build());
    }
}
