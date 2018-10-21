package com.example.hasantarek.vaccinedetailasandschedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hasan Tarek on 10/12/2017.
 */
public class UserDbHelper extends SQLiteOpenHelper {

    public static final String database_name = "vaccine.db";
    private final String userinfo_table = "user_info";
    private final String userinfo_col1 = "id";
    private final String userinfo_col2 = "name";
    private final String userinfo_col3 = "birthdate";
    private final String userinfo_col4 = "user_category";

    private final String vaccineinfo_table = "vaccine_info";
    private final String vaccineinfo_col1 = "id";
    private final String vaccineinfo_col2 = "name_id";
    private final String vaccineinfo_col3 = "vaccine";
    private final String vaccineinfo_col4 = "vaccine_date";
    private final String vaccineinfo_col5 = "given_date";
    private final String vaccineinfo_col6 = "given_status";


    public UserDbHelper(Context context) {
        super(context, database_name, null, 1);
        SQLiteDatabase db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + userinfo_table + " ("
                + userinfo_col1 + " INTEGER, "
                + userinfo_col2 + " TEXT NOT NULL, "
                + userinfo_col3 + " TEXT DEFAULT 'null', "
                + userinfo_col4 + " TEXT NOT NULL);");


        db.execSQL("CREATE TABLE " + vaccineinfo_table + " ("
                + vaccineinfo_col1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + vaccineinfo_col2 + " INTEGER, "
                + vaccineinfo_col3 + " TEXT NOT NULL, "
                + vaccineinfo_col4 + " TEXT NOT NULL, "
                + vaccineinfo_col5 + " TEXT DEFAULT 'null', "
                + vaccineinfo_col6 + " TEXT DEFAULT 'null');");

               // + KEY_WORKED + " INTEGER, "
               // + KEY_NOTE + " INTEGER DEFAULT 0);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + userinfo_table);
        db.execSQL("drop table if exists " + vaccineinfo_table);
        onCreate(db);
    }

    public boolean adduser(int id,String name,String Birthdate,String userCategory)
    {
        SQLiteDatabase sqLiteDatabase  = getWritableDatabase();
        ContentValues contentvalue = new ContentValues();
        contentvalue.put(userinfo_col1, id);
        contentvalue.put(userinfo_col2, name);
        contentvalue.put(userinfo_col3, Birthdate);
        contentvalue.put(userinfo_col4, userCategory);
        long result = sqLiteDatabase.insert(userinfo_table, null, contentvalue);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean addVaccinedate(int id,String Vacname,String Vdate)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(vaccineinfo_col2,id);
        contentValues.put(vaccineinfo_col3,Vacname);
        contentValues.put(vaccineinfo_col4,Vdate);
        long res = sqLiteDatabase.insert(vaccineinfo_table, null, contentValues);
        if(res == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public Cursor getUser(String category)
    {
        SQLiteDatabase sqLiteDatabase  = getWritableDatabase();
        //Cursor res = sqLiteDatabase.rawQuery("select * from " + userinfo_table + " where id = " + 1, null);
        //category="women";
        String query= "SELECT * FROM user_info WHERE user_category LIKE '%" + category + "%'";
        Cursor res = sqLiteDatabase.rawQuery(query, null);
        return  res;
    }

    public Cursor findUser(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + userinfo_table + " where id = " + id, null);
        return res;
    }


    public Cursor getVaccineInfo(int id)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
       // Cursor res = sqLiteDatabase.rawQuery("select * from " + vaccineinfo_table, null);
        Cursor res = sqLiteDatabase.rawQuery("select * from " + vaccineinfo_table +
                " where "+ vaccineinfo_col2 + " = " + id,null);
        return res;
    }

    public Cursor getVaccineNotification(String d)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query= "SELECT * FROM vaccine_info WHERE vaccine_date LIKE '%" + d + "%'";
        Cursor res = sqLiteDatabase.rawQuery(query,null);
        return res;
    }
    public int deletedata(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int res1 = db.delete(userinfo_table, "id=?", new String[]{id});
        int res2 =  db.delete(vaccineinfo_table, "name_id=?", new String[]{id});
        if(res1>0 && res2>0)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }


    public int update(int id,String gdate,String gstatus)
    {
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(vaccineinfo_col5,gdate);
        contentValues.put(vaccineinfo_col6, gstatus);

       int count =  sqLiteDatabase.update(vaccineinfo_table,
                contentValues,
                vaccineinfo_col1 + " = ? ",
                new String[]{String.valueOf(id)});

        return count;
    }
    public void resetDatabase() {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS " + userinfo_table);
        database.execSQL("DROP TABLE IF EXISTS " + vaccineinfo_table);
        onCreate(database);
        database.close();
    }
}