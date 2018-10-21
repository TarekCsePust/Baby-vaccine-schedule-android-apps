package com.example.hasantarek.vaccinedetailasandschedule;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class VaccineDetails extends AppCompatActivity {

    Toolbar toolbar;
    Spinner spinner;
    EditText hospital_name;
    TextView given_date,vaccine_name;
    ArrayAdapter<CharSequence> adapter;
    String GivenStatus="";
    String GivenDate = "";
    String Hospital="";
    UserDbHelper userDbHelper;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_details);
        userDbHelper = new UserDbHelper(this);
        toolbar = (Toolbar)findViewById(R.id.toolbar_id);
        spinner = (Spinner)findViewById(R.id.gs_id);
        vaccine_name = (TextView)findViewById(R.id.vname_id);
        hospital_name = (EditText)findViewById(R.id.hospital_id);
        given_date = (TextView)findViewById(R.id.gdate_id);
        Intent intent = getIntent();
        String vaccine = intent.getStringExtra("vaccine");
        id = intent.getIntExtra("vaccine_id", 0);
        GivenDate =  intent.getStringExtra("givendate");
        GivenStatus = intent.getStringExtra("givenstatus");
        vaccine_name.setText(vaccine);
        toolbar.setTitle("Vaccine Details");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.backbtn);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        adapter = ArrayAdapter.createFromResource(this,R.array.given_status,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if(GivenDate.equals("null"))
        {
            given_date.setText("dd-mm-yyyy");
        }
        else
        {
            given_date.setText(GivenDate);
        }
        if(GivenStatus.equals("null") || GivenStatus.equals("NO"))
        {
            spinner.setSelection(adapter.getPosition("NO"));
            given_date.setText("dd-mm-yyyy");
        }
        else
        {
            spinner.setSelection(adapter.getPosition("YES"));
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position) + "", Toast.LENGTH_LONG).show();
                GivenStatus = parent.getItemAtPosition(position)+"";
                if(GivenStatus.equals("NO"))
                {
                    given_date.setText("dd-mm-yyyy");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    public void getdate(View view)
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

                        monthOfYear = monthOfYear+1;
                        //Toast.makeText(getApplicationContext(), dayOfMonth + "-"
                       // + (monthOfYear) + "-" + year, Toast.LENGTH_SHORT).show();
                        given_date.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
                        /*if(vac.equals("childvaccine"))
                        {

                            birthdate.setText(dayOfMonth+"-"+monthOfYear+"-"+year);
                        }
                        else
                        {
                            vaccinedate.setText(dayOfMonth+"-"+monthOfYear+"-"+year);
                        }*/

                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }





    public void update(View view)
    {
        if(GivenDate.equals("dd-mm-yyyy"))
        {
            GivenDate = "null";
        }
        else
        {
            GivenDate = given_date.getText().toString();
        }

      //  Toast.makeText(getApplicationContext(),GivenDate,Toast.LENGTH_SHORT).show();
        if(GivenStatus.equals("YES") && (GivenDate.equals("null") || GivenDate.equals("dd-mm-yyyy")))
        {
            Toast.makeText(getApplicationContext(),"Date is required",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Hospital = hospital_name.getText().toString();
            int res = userDbHelper.update(id,GivenDate,GivenStatus);
            if(res>0)
            {
                Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),VaccineInfoList.class));
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Not updated",Toast.LENGTH_SHORT).show();
            }
        }

    }


}
