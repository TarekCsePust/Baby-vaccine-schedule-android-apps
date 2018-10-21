package com.example.hasantarek.vaccinedetailasandschedule;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Hasan Tarek on 10/14/2017.
 */
public class Notification extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context,MainActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        UserDbHelper userDbHelper = new UserDbHelper(context);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        String date =day+"-"+month+"-"+year;

        Cursor cursor = userDbHelper.getVaccineNotification(date);

        /*int ids[] = new int[25];
        String vaccines[][]=new String[10][10];

        int row=0,col=0;
        if(cursor.getCount()!=0)
        {
            int Cid =0,Pid = 0;

            int p=0;
            while (cursor.moveToNext())
            {
                Cid = cursor.getInt(1);
                if(p==0)
                {
                    vaccines[row][col++]=cursor.getString(2);
                    p=1;
                    Pid = Cid;
                    ids[row]=Cid;
                }
                else
                {
                    if(Cid == Pid)
                    {
                        vaccines[row][col++] = cursor.getString(2);
                    }
                    else
                    {
                        col=0;
                        vaccines[row++][col++]=cursor.getString(2);
                        Pid = Cid;
                        ids[row] = Cid;
                    }
                }


            }
        }
       String names[] =new String[20];
        for (int k=0;k<row;k++)
        {
            Cursor cursor1 =  userDbHelper.findUser(ids[k]);
            if(cursor1.getCount()!=0)
            {
                while (cursor1.moveToNext())
                {
                    names[k] = cursor1.getString(1);
                }

            }
        }

       String notiF="";
       for(int k=0;k<row;k++)
       {
           notiF+= names[k] + " you need to take ";
           for (String s:vaccines[k])
           {
               if(s!=null)
               {
                   notiF+=s+" ";
               }
           }
           notiF+="vaccine\n";
       }*/

        if(cursor.getCount()!=0)
        {
            String notiF="Today you have vaccine taken schedule please check your" +
                    " vaccination list";
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            //String info = "You need to take vaccine today check your vaccine schedule.";
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.app_icon)
                    .setContentTitle("Vaccine Details")
                    .setContentText(Html.fromHtml(notiF))
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(notiF))
                    .setAutoCancel(true)
                    .setSound(soundUri)
                    ;
            notificationManager.notify(100,builder.build());

        }

    }
}