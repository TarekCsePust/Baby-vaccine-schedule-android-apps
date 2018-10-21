package com.example.hasantarek.vaccinedetailasandschedule;

import java.util.Calendar;

/**
 * Created by Hasan Tarek on 10/13/2017.
 */
public class ProvideVaccineDate {

    int day;
    int month;
    int year;
    int Pday,Pmonth,Pyear;

    public ProvideVaccineDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void setPdate(int d,int m,int y)
    {
        this.Pday = d;
        this.Pmonth = m;
        this.Pyear = y;
    }
    public void DateCalculate()
    {


        Pday = Pday + day;
        if(Pday>30)
        {
            Pday = Pday - 30;
            Pmonth = Pmonth + 1;
        }
        Pmonth = Pmonth + month;
        if(Pmonth>12)
        {
            Pmonth = Pmonth - 12;
            Pyear = Pyear + 1;
        }

        Pyear = Pyear + year;

        if(month == 2)
        {
            if(Pday>28)
            {
                Pday = 28;
            }
        }
    }

    public int getDay() {
        return Pday;
    }

    public int getMonth() {
        return Pmonth;
    }

    public int getYear() {
        return Pyear;
    }
}