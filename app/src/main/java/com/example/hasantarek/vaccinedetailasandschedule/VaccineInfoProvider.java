package com.example.hasantarek.vaccinedetailasandschedule;

/**
 * Created by Hasan Tarek on 10/13/2017.
 */
public class VaccineInfoProvider {

    String vaccine;
    String vaccineDate;
    String givenDate;
    String givenStatus;
    int id;
    int name_id;

    public VaccineInfoProvider(String vaccine, String vaccineDate, String givenDate, int id, String givenStatus, int name_id) {
        this.vaccine = vaccine;
        this.vaccineDate = vaccineDate;
        this.givenDate = givenDate;
        this.id = id;
        this.givenStatus = givenStatus;
        this.name_id = name_id;
    }

    public String getVaccine() {
        return vaccine;
    }

    public String getVaccineDate() {
        return vaccineDate;
    }

    public String getGivenDate() {
        return givenDate;
    }

    public String getGivenStatus() {
        return givenStatus;
    }

    public int getId() {
        return id;
    }

    public int getName_id() {
        return name_id;
    }
}