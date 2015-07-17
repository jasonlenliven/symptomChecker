package com.doit.can.you.symptomcheck.events;

import com.doit.can.you.symptomcheck.models.Symptom;

import java.util.List;

/**
 * Created by jason.le on 7/17/15.
 */
public class SymptomEvent {
    private List<Symptom> symptoms = null;
    private int statusCode = 0;

    public SymptomEvent(List<Symptom> symptoms, int statusCode) {
        this.symptoms = symptoms;
        this.statusCode = statusCode;
    }

    public List<Symptom> getSymptoms(){
        return symptoms;
    }

    public int getStatusCode() { return statusCode; }
}
