package com.doit.can.you.symptomcheck.events;

import com.doit.can.you.symptomcheck.models.Diagnosis;
import com.doit.can.you.symptomcheck.models.Symptom;

import java.util.List;

/**
 * Created by jason.le on 7/17/15.
 */
public class DiagnosesResponseEvent {
    private List<Diagnosis> diagnoses = null;
    private int statusCode = 0;

    public DiagnosesResponseEvent(List<Diagnosis> symptoms, int statusCode) {
        this.diagnoses = symptoms;
        this.statusCode = statusCode;
    }

    public List<Diagnosis> getDiagnoses(){
        return diagnoses;
    }

    public int getStatusCode() { return statusCode; }
}
