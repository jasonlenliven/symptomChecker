package com.doit.can.you.symptomcheck.models;

import java.util.ArrayList;

/**
 * Created by jason.le on 7/16/15.
 */
public class SearchCriteria {
    String ageGroup;

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(ArrayList<String> symptoms) {
        this.symptoms = symptoms;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    String gender;

    ArrayList<String> symptoms;
}
