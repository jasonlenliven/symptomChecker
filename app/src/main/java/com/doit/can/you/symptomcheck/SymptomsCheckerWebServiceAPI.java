package com.doit.can.you.symptomcheck;

import com.doit.can.you.symptomcheck.models.Diagnosis;
import com.doit.can.you.symptomcheck.models.SearchCriteria;
import com.doit.can.you.symptomcheck.models.Symptom;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by jason.le on 7/16/15.
 */
public interface SymptomsCheckerWebServiceAPI {

    @GET("/diag/symptoms")
    void getSymptoms( Callback<List<Symptom>> cb) throws RetrofitError;

    @POST("/diag/symptoms")
    void postSymptoms(@Body SearchCriteria criteria, Callback<List<Diagnosis>> cb) throws RetrofitError;
}
