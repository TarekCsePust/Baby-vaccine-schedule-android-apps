package com.example.hasantarek.vaccinedetailasandschedule;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    android.support.v7.app.ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;

    //UserDbHelper userDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // userDbHelper = new UserDbHelper(this);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,
                drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container,new Home());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Home");

        navigationView = (NavigationView)findViewById(R.id.navigation_id);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home_menu:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new Home());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Home");
                        //item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.child_menu:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new ChildVaccine());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("CHILD VACCINE");
                        //item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.women_menu:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new WomenVaccine());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("WOMEN VACCINE");
                        //item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.other_menu:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new OtherVaccine());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("OTHER VACCINE");
                        //item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    default:
                        return true;

                }
                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.child_list_id:

                //Toast.makeText(getApplicationContext(),"child list",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),NameList.class);
                intent.putExtra("category","child");
                startActivity(intent);
                break;
            case R.id.women_list_id:
               // Toast.makeText(getApplicationContext(),"women list",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(),NameList.class);
                intent1.putExtra("category", "women");
                startActivity(intent1);
                break;
            case R.id.other_list_id:
                Toast.makeText(getApplicationContext(),"other list",Toast.LENGTH_SHORT).show();
                break;
            default:
                return true;
        }
        return true;
    }



}
