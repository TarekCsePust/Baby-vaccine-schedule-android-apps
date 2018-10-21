package com.example.hasantarek.vaccinedetailasandschedule;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AddHuman extends AppCompatActivity {

    ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
    int Pickday,Pickmonth,Pickyear;
    ArrayAdapter<CharSequence>adapter;
    Toolbar toolbar;
    TextView birthdate,vaccinedate;
    EditText name;
    UserDbHelper userDbHelper;
    int id;
    int nid;
    UniqeIdProvider uniqeIdProvider;
    TextView text_1,text_2,text_3,text_4;
    Button birthbtn,vacbtn,savebtn,showbtn;
    Spinner spinner;
    ProvideVaccineDate provideVaccineDate;
    String vac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_human);

        Intent intent = getIntent();
        vac = intent.getStringExtra("vaccine");
        //Toast.makeText(getApplicationContext(),vac,Toast.LENGTH_SHORT).show();

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
        savebtn = (Button)findViewById(R.id.savebtn);
        showbtn = (Button)findViewById(R.id.showbtn);

        if(vac.equals("childvaccine"))
        {
            text_3.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
            text_4.setVisibility(View.GONE);
            vaccinedate.setVisibility(View.GONE);
            vacbtn.setVisibility(View.GONE);

            text_2.setVisibility(View.VISIBLE);
            birthdate.setVisibility(View.VISIBLE);
            birthbtn.setVisibility(View.VISIBLE);


        }
        else
        if(vac.equals("womenvaccine"))
        {
            text_2.setVisibility(View.GONE);
            birthdate.setVisibility(View.GONE);
            birthbtn.setVisibility(View.GONE);

            text_3.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
            text_4.setVisibility(View.VISIBLE);
            vaccinedate.setVisibility(View.VISIBLE);
            vacbtn.setVisibility(View.VISIBLE);
        }
        else
        if(vac.equals("othervaccine"))
        {
            text_2.setVisibility(View.GONE);
            birthdate.setVisibility(View.GONE);
            birthbtn.setVisibility(View.GONE);

            text_3.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);
            text_4.setVisibility(View.VISIBLE);
            vaccinedate.setVisibility(View.VISIBLE);
            vacbtn.setVisibility(View.VISIBLE);
        }



       // Toast.makeText(getApplicationContext(),childVacDates[0][0]+"-"+childVacDates[0][1]+"-"+
        //childVacDates[0][2],Toast.LENGTH_SHORT).show();


        uniqeIdProvider =  new UniqeIdProvider(getApplicationContext());
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences
                ("pref_file", Context.MODE_PRIVATE);
        id = sharedPreferences.getInt("id",0);
        nid = sharedPreferences.getInt("noti_id",0);

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

       // Toast.makeText(getApplicationContext(),id+"",Toast.LENGTH_SHORT).show();


        adapter = ArrayAdapter.createFromResource(this,R.array.vaccines,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),parent.getItemAtPosition(position)+"",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String date = "11-9-2017";
        String[] input = date.split("-");
        int day = Integer.valueOf(input[0]);
        int month = Integer.valueOf(input[1]);
        int year = Integer.valueOf(input[2]);


        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month-1);
        cal.set(Calendar.DATE, day);
        //since month number starts from 0 (i.e jan 0, feb 1),
        //we are subtracting original month by 1
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
      // Toast.makeText(this,days+"",Toast.LENGTH_LONG).show();

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        //Toast.makeText(getApplicationContext(),mDay+"-"+mMonth+"-"+mYear,Toast.LENGTH_SHORT).show();


        //provideVaccineDate = new ProvideVaccineDate(20,2,0);
        //provideVaccineDate.DateCalculate();
       // Toast.makeText(getApplicationContext(),provideVaccineDate.getDay()+"-"+provideVaccineDate.getMonth()+"-"+provideVaccineDate.getYear(),Toast.LENGTH_SHORT).show();

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(vac.equals("childvaccine"))
                {
                    if(name.getText().toString().equals("") || birthdate.getText().toString().equals("dd-mm-yyyy"))
                    {
                        Toast.makeText(getApplicationContext(),"Name and birthdate is required",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        SaveChildInfo();
                    }

                }
                else
                if(vac.equals("womenvaccine"))
                {
                    if(name.getText().toString().equals("") || vaccinedate.getText().toString().equals("dd-mm-yyyy"))
                    {
                        Toast.makeText(getApplicationContext(),"Name and vaccine date is required",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        SaveWomenInfo();
                    }

                }
                else
                {
                    SaveOtherInfo();
                }

            }
        });
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

                        monthOfYear = monthOfYear+1;
                        //Toast.makeText(getApplicationContext(), dayOfMonth + "-"
                               // + (monthOfYear) + "-" + year, Toast.LENGTH_SHORT).show();
                        Pickday = dayOfMonth;
                        Pickmonth = monthOfYear;
                        Pickyear = year;
                        if(vac.equals("childvaccine"))
                        {

                            birthdate.setText(dayOfMonth+"-"+monthOfYear+"-"+year);
                        }
                        else
                        {
                            vaccinedate.setText(dayOfMonth+"-"+monthOfYear+"-"+year);
                        }

                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }



   /* public void savedata(View view)
    {
        boolean result = userDbHelper.adduser(1, name.getText().toString(), birthdate.getText().toString(), "child");
        if(result)
        {
            Toast.makeText(getApplicationContext(),"save",Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),id+"",Toast.LENGTH_SHORT).show();
            id++;
            uniqeIdProvider.setId(id);

        }
        else
        {
            Toast.makeText(getApplicationContext(),"not save",Toast.LENGTH_LONG).show();
        }
    }*/

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


        Cursor cursor1 = userDbHelper.getVaccineInfo(5);


        int vc = cursor1.getCount();
        if(vc == 0)
        {
            Toast.makeText(getApplicationContext(),"no data",Toast.LENGTH_LONG).show();
        }
        else
        {
            String vaccins="";
            while (cursor1.moveToNext())
            {

                vaccins +="id: " + cursor1.getString(0) + "\n"+"name_id: " + cursor1.getString(1)
                        + "\n" + "Vaccine: " + cursor1.getString(2) +
                        "Vaccinedate: " + cursor1.getString(3) +
                        "Givendate: " + cursor1.getString(4) +
                        "GivenStatus: " + cursor1.getString(5) +
                        "\n\n";

                //data.append("id: " + res.getString(0) + "\n");
                //data.append("name: " + res.getString(1) + "\n");
                // data.append("roll: " + res.getString(2) + "\n");
                // data.append("mark: " + res.getString(3) + "\n\n");
            }

            Toast.makeText(getApplicationContext(),vaccins,Toast.LENGTH_LONG).show();
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

    public void  SaveChildInfo()
    {
        id++;
        //Toast.makeText(getApplicationContext(),id+"",Toast.LENGTH_SHORT).show();
        uniqeIdProvider.setId(id,nid);
        String vaccine[][] = {{"BCG","OPV-0"},
                               {"Penta-1","Pcv-1","Opv-1"},
                                {"Penta-2","Pcv-2","Opv-2"},
                                {"Penta-3","Pcv-3","Opv-3"},
                                {"MR-1"},{"MR-2"}
                             };
        int childVacDates[][]={{0,0,0},{14,1,0},{12,2,0},{10,3,0},{2,9,0},{2,3,1}};

        //Toast.makeText(getApplicationContext(),vaccine[0][0]+" "+vaccine[0][1],Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),Pickday+"-"+Pickmonth+"-"+Pickyear,Toast.LENGTH_SHORT).show();
        String Bdate  = birthdate.getText().toString();
        String Cname = name.getText().toString();
        String Category = "child";
        boolean result = userDbHelper.adduser(id,Cname,Bdate,Category);
        boolean res=false;
        Calendar calendar[]=new Calendar[100];
        ProvideVaccineDate provideVaccineDate = new ProvideVaccineDate(Pickday,Pickmonth,Pickyear);
        for(int i=0;i<6;i++)
        {
            provideVaccineDate.setPdate(childVacDates[i][0], childVacDates[i][1],
                    childVacDates[i][2]);
            provideVaccineDate.DateCalculate();
            calendar[i] = Calendar.getInstance();
            calendar[i].set(Calendar.YEAR,provideVaccineDate.getYear());
            calendar[i].set(Calendar.MONTH,provideVaccineDate.getMonth()-1);
            calendar[i].set(Calendar.DAY_OF_MONTH,provideVaccineDate.getDay());
            calendar[i].set(Calendar.HOUR_OF_DAY,0);
            calendar[i].set(Calendar.MINUTE,1);
            calendar[i].set(Calendar.SECOND,0);
            String date= provideVaccineDate.getDay()+"-"+provideVaccineDate.getMonth()+"-"+
                    provideVaccineDate.getYear();


            for(String v:vaccine[i])
            {
                res = userDbHelper.addVaccinedate(id,v,date);


            }


            //Toast.makeText(getApplicationContext(),date,Toast.LENGTH_SHORT).show();
        }

        //uniqeIdProvider.setId(id,nid);
        if(result && res)
        {
            Toast.makeText(getApplicationContext(),"save",Toast.LENGTH_SHORT).show();
            name.setText("");
            birthdate.setText("dd-mm-yyyy");
        }

        set_notification(calendar,6);
    }


    public void set_notification(Calendar calendar[],int count)
    {
        for(int i=0;i<count;i++)
        {
            nid++;
            Intent intent = new Intent(getApplicationContext(),Notification.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),nid,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,calendar[i].getTimeInMillis(),pendingIntent);
            intentArray.add(pendingIntent);
        }

        uniqeIdProvider.setId(id,nid);

    }

    public void SaveWomenInfo()
    {
        id++;
        uniqeIdProvider.setId(id, nid);
        String Vdate  = vaccinedate.getText().toString();
        String Cname = name.getText().toString();
        String Category = "women";
        boolean result = userDbHelper.adduser(id, Cname,"null", Category);
        String Wvaccine[]= {"TT-1","TT-2","TT-3","TT-4",
                "TT-5"
        };
        int childVacDates[][]={{0,0,0},{30,0,0},{28,6,0},{28,6,1},{28,6,2}};
        boolean res = false;
        Calendar calendar[]=new Calendar[100];
        ProvideVaccineDate provideVaccineDate = new ProvideVaccineDate(Pickday,Pickmonth,Pickyear);
        for(int i=0;i<5;i++)
        {
            provideVaccineDate.setPdate(childVacDates[i][0], childVacDates[i][1],
                    childVacDates[i][2]);
            provideVaccineDate.DateCalculate();
            calendar[i] = Calendar.getInstance();
            calendar[i].set(Calendar.YEAR,provideVaccineDate.getYear());
            calendar[i].set(Calendar.MONTH,provideVaccineDate.getMonth()-1);
            calendar[i].set(Calendar.DAY_OF_MONTH,provideVaccineDate.getDay());
            calendar[i].set(Calendar.HOUR_OF_DAY,0);
            calendar[i].set(Calendar.MINUTE,1);
            calendar[i].set(Calendar.SECOND,0);
            String date= provideVaccineDate.getDay()+"-"+provideVaccineDate.getMonth()+"-"+
                    provideVaccineDate.getYear();

                res = userDbHelper.addVaccinedate(id,Wvaccine[i],date);

        }



        if(result && res)
        {
            Toast.makeText(getApplicationContext(),"save",Toast.LENGTH_SHORT).show();
            name.setText("");
            vaccinedate.setText("dd-mm-yyyy");
        }

        set_notification(calendar,5);

    }

    public void SaveOtherInfo()
    {

    }

}
