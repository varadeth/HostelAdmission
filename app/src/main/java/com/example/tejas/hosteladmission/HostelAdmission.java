package com.example.tejas.hosteladmission;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Tejas on 03-09-2017.
 */

public class HostelAdmission extends Application {
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
