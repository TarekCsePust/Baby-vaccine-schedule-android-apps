package com.example.hasantarek.vaccinedetailasandschedule;

/**
 * Created by Hasan Tarek on 10/9/2017.
 */
public class NameProvider {

    String name;
    int id;
    String birthdate;

    public NameProvider(String name, int id, String birthdate) {
        this.name = name;
        this.id = id;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getBirthdate() {
        return birthdate;
    }
}