package com.god.parktaeim.all_about_sejong;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by parktaeim on 2018. 7. 6..
 */

public class LeakCanaryApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
