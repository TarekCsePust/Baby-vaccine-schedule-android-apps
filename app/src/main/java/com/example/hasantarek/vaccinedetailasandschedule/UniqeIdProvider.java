package com.example.hasantarek.vaccinedetailasandschedule;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hasan Tarek on 10/13/2017.
 */
public class UniqeIdProvider {

    Context context;

    public UniqeIdProvider(Context context) {
        this.context = context;
    }

    public void setId(int id,int nid)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences
                ("pref_file",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id",id);
        editor.putInt("noti_id",nid);
        editor.commit();
    }
}