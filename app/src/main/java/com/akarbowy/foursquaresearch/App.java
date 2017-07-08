package com.akarbowy.foursquaresearch;


import android.app.Application;

import com.akarbowy.foursquaresearch.di.AppComponent;
import com.akarbowy.foursquaresearch.di.DaggerAppComponent;

public class App extends Application {

    private AppComponent appComponent;

    @Override public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getComponent() {
        return appComponent;
    }
}
