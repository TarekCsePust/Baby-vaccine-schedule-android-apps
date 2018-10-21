package com.example.hasantarek.vaccinedetailasandschedule;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddChild extends AppCompatActivity {

    Toolbar toolbar;
    TextView birthdate,vaccinedate;
    EditText name;
    UserDbHelper userDbHelper;
    int c;
    UniqeIdProvider uniqeIdProvider;
    TextView text_1,text_2,text_3,text_4;
    Button birthbtn,vacbtn;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
        toolbar = (Toolbar)findViewById(R.id.toolbar_id);
        birthdate = (TextView)findViewById(R.id.birth_id);
        vaccinedate = (TextView)findViewById(R.id.vaccine_date_id);
        spinner = (Spinner)findViewById(R.id.vaccine_name_id);
        name = (EditText)findViewById(R.id.name_id);
        text_1 = (TextView)findViewById(R.id.text1);
        text_2 = (TextView)findViewById(R.id.text2);
        text_3 = (TextView)findViewById(R.id.text3);
        text_4 = (TextView)findViewById(R.id.text4);
        birthbtn = (Button)findViewById(R.id.birthbtn);
        vacbtn = (Button)findViewById(R.id.vacbtn);


        text_2.setVisibility(View.GONE);
        birthdate.setVisibility(View.GONE);
        birthbtn.setVisibility(View.GONE);


        text_3.setVisibility(View.GONE);
        spinner.setVisibility(View.GONE);


        text_4.setVisibility(View.GONE);
        vaccinedate.setVisibility(View.GONE);
        vacbtn.setVisibility(View.GONE);

        uniqeIdProvider =  new UniqeIdProvider(getApplicationContext());
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences
                ("pref_file", Context.MODE_PRIVATE);
        c = sharedPreferences.getInt("id",0);

        userDbHelper = new UserDbHelper(this);
        toolbar.setTitle("ADD TO LIST");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.backbtn);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Toast.makeText(getApplicationContext(),c+"",Toast.LENGTH_SHORT).show();

    }


    public void Pickdate(View view)
    {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        Toast.makeText(getApplicationContext(), dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year, Toast.LENGTH_SHORT).show();

                        birthdate.setText(dayOfMonth+"-"+monthOfYear+"-"+year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }


    public void savedata(View view)
    {
        boolean result = userDbHelper.adduser(1, name.getText().toString(), birthdate.getText().toString(), "child");
        if(result)
        {
            Toast.makeText(getApplicationContext(),"save",Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),c+"",Toast.LENGTH_SHORT).show();
           c++;
           //uniqeIdProvider.setId(c);

        }
        else
        {
            Toast.makeText(getApplicationContext(),"not save",Toast.LENGTH_LONG).show();
        }
    }

    public void showdata(View view)
    {
        Cursor cursor = userDbHelper.getUser("child");
        int count = cursor.getCount();
        if(count == 0)
        {
            Toast.makeText(getApplicationContext(),"no data",Toast.LENGTH_LONG).show();
        }
        else
        {
            String users="";
            while (cursor.moveToNext())
            {

                users +="id: " + cursor.getString(0) + "\n"+"name: " + cursor.getString(1)
                        + "\n" + "Birthdate: " + cursor.getString(2) + "\n\n";

                //data.append("id: " + res.getString(0) + "\n");
                //data.append("name: " + res.getString(1) + "\n");
                // data.append("roll: " + res.getString(2) + "\n");
                // data.append("mark: " + res.getString(3) + "\n\n");
            }

            Toast.makeText(getApplicationContext(),users,Toast.LENGTH_SHORT).show();
        }

    }

    public void deletedata(View view)
    {
        int result = userDbHelper.deletedata(Integer.toString(2));
        if(result>0)
        {
            Toast.makeText(getApplicationContext(),"Deletd",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Not Deletd",Toast.LENGTH_SHORT).show();
        }
    }

    public void resetdata(View view)
    {
        userDbHelper.resetDatabase();
        Toast.makeText(getApplicationContext(),"reset data",Toast.LENGTH_SHORT).show();
    }

}
