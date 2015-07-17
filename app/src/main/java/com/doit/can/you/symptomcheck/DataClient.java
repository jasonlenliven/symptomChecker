package com.doit.can.you.symptomcheck;

import android.util.Log;

import com.doit.can.you.symptomcheck.events.SymptomEvent;
import com.doit.can.you.symptomcheck.models.Symptom;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by jason.le on 7/17/15.
 */
public class DataClient {

    private static final String TAG = DataClient.class.getSimpleName();

    private SymptomsCheckerWebServiceAPI mServiceAPI;

    protected DataClient()
    {
        this(SymptomCheckerApplication.getWebserviceAPI());
    }

    public DataClient(SymptomsCheckerWebServiceAPI serviceAPI)
    {
        mServiceAPI = serviceAPI;
    }

    public static DataClient GetInstance()
    {
        return new DataClient(SymptomCheckerApplication.getWebserviceAPI());
    }

    public void getSymptoms() {
        mServiceAPI.getSymptoms(new Callback<List<Symptom>>() {

            @Override
            public void success(List<Symptom> data, Response response) {
                Log.i(TAG, "Hit URL: " + response.getUrl());
                Log.i(TAG, "Received data: " + data);
                SymptomCheckerApplication.getEventBus().post(new SymptomEvent(data, response.getStatus()));
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i(TAG, "UNABLE TO HIT WS");
                Log.i(TAG, "URL " + retrofitError.getUrl());
                Log.i(TAG, "isNetworkError " + retrofitError.isNetworkError());
                if (retrofitError.getResponse() != null) {
                    Log.i(TAG, "REASON: " + retrofitError.getResponse().getReason());
                    Log.i(TAG, "STATUS " + retrofitError.getResponse().getStatus());
                }
            }
        });
    }
}
