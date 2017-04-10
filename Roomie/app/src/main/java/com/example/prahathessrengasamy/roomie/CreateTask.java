package com.example.prahathessrengasamy.roomie;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;

import android.app.Notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;

import android.support.annotation.RequiresApi;
import android.support.v7.app.NotificationCompat;

import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;


import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by Llewellyn on 3/5/17.
 */

public class CreateTask extends Activity implements View.OnClickListener {

    private EditText title;
    private EditText des;
    private Spinner cat;
    private EditText dd;
    private DatePickerDialog due_date;
    private SimpleDateFormat sdf;
    private spinAdapter roomies;
    private Button submit,list;
    private RatingBar priority;

    private RatingBar effort;
    private int count=0;

    private DatabaseReference mDatabase;
    ArrayList<shoppinglist> items;
    String uuid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task);
        sdf=new SimpleDateFormat("MM-dd-yyyy", Locale.US);
        findViewsById();
        setDateTimeField();
        roomies = (spinAdapter) findViewById(R.id.roomies);
        cat = (Spinner) findViewById(R.id.cat);
        submit = (Button) findViewById(R.id.back);
        title = (EditText) findViewById(R.id.title);
        des = (EditText) findViewById(R.id.des);
        list = (Button) findViewById(R.id.list);
        priority = (RatingBar) findViewById(R.id.priority);
        addItemsonCat();
        addItemsonRoomies();
       // String uuid =UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        genuuid();
final Context c=this;

        cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                if(selectedItem.equals("Shopping"))
                {
                    list.setVisibility(View.VISIBLE);
                }
                else{
                    list.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v) {
                // Perform action on click
                mDatabase = FirebaseDatabase.getInstance().getReference();
                Tasks p = new Tasks(title.getText().toString(),des.getText().toString(),cat.getSelectedItem().toString(),dd.getText().toString(),roomies.getSelectedItem().toString(),priority.getRating());
               Toast t= Toast.makeText(getApplicationContext(),"Task Added",Toast.LENGTH_LONG);
                t.show();
                mDatabase.child("tasks").child(uuid).setValue(p);

               /* int   day  = due_date.getDatePicker().getDayOfMonth();
                int   month= due_date.getDatePicker().getMonth();
                int   year = due_date.getDatePicker().getYear();

                android.icu.util.Calendar date = android.icu.util.Calendar.getInstance();
                date.set(android.icu.util.Calendar.DAY_OF_MONTH,due_date.getDatePicker().getDayOfMonth());
                date.set(Calendar.HOUR_OF_DAY, 12);
                date.set(Calendar.MINUTE, 00);
                date.set(Calendar.SECOND, 00);*/
                mDatabase.child("shopping/" + uuid).child("/items").setValue(items);
               // remind("Task Due Today:"+title.getText().toString(),des.getText().toString());
                CreateTask.super.onBackPressed();


                    mDatabase.child("shopping/" + uuid).child("/items").setValue(items);

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date startDate=new Date();
                try {
                    startDate = sdf.parse(dd.getText().toString());
                    String newDateString = sdf.format(startDate);
                    System.out.println(newDateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date date = new Date();
                long aa=Math.abs(startDate.getTime() - date.getTime());

                //scheduleNotification(FirebaseInstanceId.getInstance().getToken(), aa);

                Log.d("dateee", aa+"   hhhaaa"+startDate+"");



                Log.d("in scheduleNotification", "");
                Intent notificationIntent = new Intent(c, datatrial.class);
                //notificationIntent.putExtra(datatrial.NOTIFICATION_ID, 1);
                notificationIntent.putExtra("nott",FirebaseInstanceId.getInstance().getToken());
                notificationIntent.putExtra("taskid",uuid);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(c, (int) System.currentTimeMillis(), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                aa=60000;
                long futureInMillis = SystemClock.elapsedRealtime() + aa;
                AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
                System.out.println("helllo");


                CreateTask.super.onBackPressed();

            }
        });
//
//        public static long daysBetween(Calendar startDate, Calendar endDate) {
//            long end = endDate.getTimeInMillis();
//            long start = startDate.getTimeInMillis();
//            return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
//        }



        list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
             /*   mDatabase = FirebaseDatabase.getInstance().getReference();
                Tasks p = new Tasks(title.getText().toString(),des.getText().toString(),cat.getSelectedItem().toString(),dd.getText().toString(),roomies.getSelectedItemsAsString(),priority.getRating());
                Toast t= Toast.makeText(getApplicationContext(),"Task Added",Toast.LENGTH_LONG);
                t.show();
                mDatabase.child("tasks").child(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase()).setValue(p);
                CreateTask.super.onBackPressed();*/
                Tasks p = new Tasks(title.getText().toString(),des.getText().toString(),cat.getSelectedItem().toString(),dd.getText().toString(),roomies.getSelectedItem().toString(),priority.getRating());

                Intent intent = new Intent(getApplicationContext(), shopping_List.class);
                intent.putExtra("uuid",uuid);
                intent.putExtra("task",p);
                startActivityForResult(intent, 1);


            }
        });
    }
    private  void remind ( String title, String message)
    {

        Intent alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
        alarmIntent.putExtra ("message", message);
        alarmIntent.putExtra ("title", title);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        //TODO: For demo set after 5 seconds.
        alarmManager.set(AlarmManager.ELAPSED_REALTIME, 3000, pendingIntent);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
               Bundle result=data.getBundleExtra("result");
                items=(ArrayList<shoppinglist> )result.getSerializable("res");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult


    private void setDateTimeField() {
        dd.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        due_date= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dd.setText(sdf.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view == dd) {
            due_date.show();
        }
    }

    private void findViewsById() {
            dd = (EditText) findViewById(R.id.dd);
            dd.setInputType(InputType.TYPE_NULL);
            dd.requestFocus();
    }


    private void genuuid(){

        uuid =UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
    private void addItemsonRoomies() {
        List<String> list = new ArrayList<String>();
        list.add("Vishal");
        list.add("Mohit");
        list.add("Prahathess");
        list.add("Dhanesh");
        roomies.setItem(list);
    }

    private void addItemsonCat() {
        List<String> list = new ArrayList<String>();

        list.add("Cooking");
        list.add("Cleaning");
        list.add("Fun");
        list.add("Shopping");
        list.add("Walking the dog");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat.setAdapter(dataAdapter);
    }


    private void scheduleNotification(String notification, long delay) {
        Log.d("in scheduleNotification", "");
        Intent notificationIntent = new Intent(this, datatrial.class);
        //notificationIntent.putExtra(datatrial.NOTIFICATION_ID, 1);
        notificationIntent.putExtra("nott",notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
delay=600;
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
    }






}
