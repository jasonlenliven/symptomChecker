package com.doit.can.you.symptomcheck.models;

public class Diagnosis {
    public String diagName;
    public String[] symptomsMatched;
    public String url;
    public double frequency;

    public Diagnosis(String name, String[] symptoms, String url) {
        this.diagName = name;
        this.symptomsMatched = symptoms;
        this.url = url;

    }
}