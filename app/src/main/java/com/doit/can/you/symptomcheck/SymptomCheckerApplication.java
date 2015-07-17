package com.doit.can.you.symptomcheck;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import retrofit.RestAdapter;

/**
 * Created by jason.le on 7/17/15.
 */
public class SymptomCheckerApplication extends Application{
    private static Context context;
    private static String mDataConnection;
    private static Bus mBus;
    private static SymptomsCheckerWebServiceAPI requestClient;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }


    public static SymptomsCheckerWebServiceAPI getWebserviceAPI() {
        //if(careMatchRequestClient == null){

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://10.0.2.2:8080/")
                .build();

        requestClient = restAdapter.create(SymptomsCheckerWebServiceAPI.class);
        // }

        return requestClient;
    }


    public static Bus getEventBus() {
        if (mBus == null) {
            mBus = new Bus(ThreadEnforcer.ANY);
        }
        return mBus;
    }
}