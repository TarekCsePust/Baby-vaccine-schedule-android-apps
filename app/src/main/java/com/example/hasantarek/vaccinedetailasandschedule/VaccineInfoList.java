package com.example.hasantarek.vaccinedetailasandschedule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VaccineInfoList extends AppCompatActivity {

    ListView listView;
    Toolbar toolbar;
    UserDbHelper userDbHelper;
    ArrayList<VaccineInfoProvider>arrayList = new ArrayList<>();
    VaccineInfoAdapter vaccineInfoAdapter;
    TextView Tname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_info_list);
        Tname = (TextView)findViewById(R.id.text);
        Intent intent = getIntent();
        int id =  intent.getIntExtra("name_id", 0);
        String name = intent.getStringExtra("name");
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                "remem_id", MODE_PRIVATE
        );
        if(id==0)
        {
            id = sharedPreferences.getInt("id",0);
            name =sharedPreferences.getString("name","null");
        }
        else
        {
            remember_id(id,name);
        }
        Tname.setText(name);
        //Toast.makeText(getApplicationContext(),id+"",Toast.LENGTH_SHORT).show();
        toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        listView =  (ListView)findViewById(R.id.vaccineinfo_list_id);
        toolbar.setTitle("Vaccine Schedule");
        toolbar.setNavigationIcon(R.mipmap.backbtn);
        userDbHelper = new UserDbHelper(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        vaccineInfoAdapter = new VaccineInfoAdapter(getApplicationContext(),R.layout.vaccine_info_row);
        listView.setAdapter(vaccineInfoAdapter);

        Cursor cursor1 = userDbHelper.getVaccineInfo(id);


        int vc = cursor1.getCount();
        if(vc == 0)
        {
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
        }
        else
        {
            //String vaccins="";
            while (cursor1.moveToNext())
            {



                VaccineInfoProvider vaccineInfoProvider = new VaccineInfoProvider(cursor1.getString(2),
                        cursor1.getString(3),cursor1.getString(4),cursor1.getInt(0),cursor1.getString(5),
                        cursor1.getInt(1));
                vaccineInfoAdapter.add(vaccineInfoProvider);
                arrayList.add(vaccineInfoProvider);


            }


        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VaccineInfoProvider vaccineInfoProvider = arrayList.get(position);
                Intent intent1 = new Intent(getApplicationContext(),VaccineDetails.class);
                intent1.putExtra("vaccine_id",vaccineInfoProvider.getId());
                intent1.putExtra("vaccine",vaccineInfoProvider.getVaccine());
                intent1.putExtra("givendate",vaccineInfoProvider.getGivenDate());
                intent1.putExtra("givenstatus",vaccineInfoProvider.getGivenStatus());
               // Toast.makeText(getApplicationContext(),vaccineInfoProvider.getVaccine() + vaccineInfoProvider.getGivenDate(),Toast.LENGTH_SHORT).show();
                startActivity(intent1);
            }
        });
    }

    public void remember_id(int id,String name)
    {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                "remem_id",MODE_PRIVATE
        );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id",id);
        editor.putString("name",name);
        editor.commit();
    }
}
