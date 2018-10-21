package com.example.hasantarek.vaccinedetailasandschedule;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NameList extends AppCompatActivity {
    ListView listView;
    NameAdapter nameAdapter;
    UserDbHelper userDbHelper;
    ArrayList <NameProvider> arrayList = new ArrayList<>();
    String names[]={"Tarek","Hasan","Habib","Akash","Shain","Tarikul","Morsalin"};
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_list);
        Intent intent = getIntent();
        String cat = intent.getStringExtra("category");
        userDbHelper = new UserDbHelper(this);
        listView = (ListView)findViewById(R.id.namelistview_id);
        //nameAdapter = new NameAdapter(getApplicationContext(),R.layout.name_row_layout);
        //listView.setAdapter(nameAdapter);
        toolbar = (Toolbar)findViewById(R.id.toolbar_id);
        if(cat.equals("child"))
        {
            toolbar.setTitle("Child List");
        }
        else
        {
            toolbar.setTitle("Women List");
        }

        toolbar.setNavigationIcon(R.mipmap.backbtn);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Cursor cursor = userDbHelper.getUser(cat);
        int count = cursor.getCount();
        if(count == 0)
        {
            Toast.makeText(getApplicationContext(),"no data",Toast.LENGTH_LONG).show();
        }
        else
        {
            //String users="";
            while (cursor.moveToNext())
            {

                NameProvider nameProvider = new NameProvider(cursor.getString(1),cursor.getInt(0),cursor.getString(2));
                //nameAdapter.add(nameProvider);
                arrayList.add(nameProvider);
                /*users +="id: " + cursor.getString(0) + "\n"+"name: " + cursor.getString(1)
                        + "\n" + "Birthdate: " + cursor.getString(2) + "\n\n";*/

                //data.append("id: " + res.getString(0) + "\n");
                //data.append("name: " + res.getString(1) + "\n");
                // data.append("roll: " + res.getString(2) + "\n");
                // data.append("mark: " + res.getString(3) + "\n\n");
            }

            //Toast.makeText(getApplicationContext(),users,Toast.LENGTH_SHORT).show();
        }



        nameAdapter = new NameAdapter(getApplicationContext(),R.layout.name_row_layout,arrayList);
        listView.setAdapter(nameAdapter);

        /*for (String name:names)
        {
            NameProvider nameProvider = new NameProvider(name);
            nameAdapter.add(nameProvider);
            arrayList.add(nameProvider);
        }*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NameProvider nameProvider = (NameProvider)arrayList.get(position);
                //Toast.makeText(getApplicationContext(),nameProvider.getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),VaccineInfoList.class);
                intent.putExtra("name_id",nameProvider.getId());
                intent.putExtra("name",nameProvider.getName());
                startActivity(intent);
            }
        });

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dialog_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int result=0;
        switch (item.getItemId())
        {
            case R.id.delete_id:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

                NameProvider nameProvider = (NameProvider)arrayList.get(info.position);
                result = userDbHelper.deletedata(String.valueOf(nameProvider.getId()));
                arrayList.remove(nameProvider);
                if(result>0)
                {
                    Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                    nameAdapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Not deleted",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(),nameProvider.getName(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit_id:
                Toast.makeText(getApplicationContext(),"edit click",Toast.LENGTH_SHORT).show();
                break;
            default:
                return true;
        }

        return true;
    }
}
