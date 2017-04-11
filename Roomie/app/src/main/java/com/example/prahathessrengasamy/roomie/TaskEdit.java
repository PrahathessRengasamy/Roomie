package com.example.prahathessrengasamy.roomie;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.InputType;
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

/**
 * Created by Llewellyn on 4/11/17.
 */

public class TaskEdit extends Activity implements View.OnClickListener {

    private EditText title;
    private EditText des;
    private Spinner cat;
    private EditText dd;
    private DatePickerDialog due_date;
    private SimpleDateFormat sdf;
    private spinAdapter roomies;
    private Button save,cancel,list;
    private RatingBar priority;
    ArrayList<shoppinglist> items;
    private DatabaseReference mDatabase;
    String uuid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskedit);
        //Intent intent=getIntent();
        Bundle bundle =getIntent().getExtras();
        title = (EditText) findViewById(R.id.title);
        des = (EditText) findViewById(R.id.des);
        cat = (Spinner) findViewById(R.id.cat);
        save=(Button) findViewById(R.id.save);
        cancel=(Button) findViewById(R.id.cancel);
        sdf = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
        findViewById();
        setDateTimeField();
        roomies = (spinAdapter) findViewById(R.id.roomies);
        list = (Button) findViewById(R.id.list);
        priority = (RatingBar) findViewById(R.id.priority);
        addItemsonCat();
        addItemsonRoomies();
        final Context c = this;
        cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                if (selectedItem.equals("Shopping")) {
                    list.setVisibility(View.VISIBLE);
                } else {
                    list.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
             /*   mDatabase = FirebaseDatabase.getInstance().getReference();
                Tasks p = new Tasks(title.getText().toString(),des.getText().toString(),cat.getSelectedItem().toString(),dd.getText().toString(),roomies.getSelectedItemsAsString(),priority.getRating());
                Toast t= Toast.makeText(getApplicationContext(),"Task Added",Toast.LENGTH_LONG);
                t.show();
                mDatabase.child("tasks").child(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase()).setValue(p);
                CreateTask.super.onBackPressed();*/
                Tasks p = new Tasks(title.getText().toString(),des.getText().toString(),cat.getSelectedItem().toString(),dd.getText().toString(),roomies.getSelectedItem().toString(),priority.getRating(),uuid);

                Intent intent = new Intent(getApplicationContext(), shopping_List.class);
                intent.putExtra("uuid",uuid);
                intent.putExtra("task",p);
                startActivityForResult(intent, 1);
            }
        });
        title.setText(bundle.getString("title"));
        des.setText(bundle.getString("des"));
        ArrayAdapter myAdap = (ArrayAdapter) cat.getAdapter();
        int spinnerPosition = myAdap.getPosition(bundle.getString("cat"));
        cat.setSelection(spinnerPosition);
        dd.setText(bundle.getString("dd"));
        uuid=bundle.getString("tid");
        String[] sel=bundle.getString("workforce").split(", ");
        roomies.setSelection(sel);
        priority.setNumStars(5);
        priority.setRating(Float.parseFloat(String.valueOf(bundle.getFloat("priority"))));
        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference();
                Tasks p = new Tasks(title.getText().toString(),des.getText().toString(),cat.getSelectedItem().toString(),dd.getText().toString(),roomies.getSelectedItem().toString(),priority.getRating(),uuid);
                Toast t= Toast.makeText(getApplicationContext(),"Task Edited",Toast.LENGTH_LONG);
                t.show();
                mDatabase.child("tasks").child(uuid).setValue(p);
                mDatabase.child("shopping/" + uuid).child("/items").setValue(items);
                TaskEdit.super.onBackPressed();
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
                Intent notificationIntent = new Intent(c, datatrial.class);
                //notificationIntent.putExtra(datatrial.NOTIFICATION_ID, 1);
                notificationIntent.putExtra("nott", FirebaseInstanceId.getInstance().getToken());
                notificationIntent.putExtra("taskid",uuid);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(c, (int) System.currentTimeMillis(), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                aa=60000;
                long futureInMillis = SystemClock.elapsedRealtime() + aa;
                AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
                System.out.println("hello");
                TaskEdit.super.onBackPressed();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v) {
                finish();

            }
        });
    }


    private void addItemsonRoomies() {
        List<String> list = new ArrayList<String>();
        list.add("Vishal");
        list.add("Mohit");
        list.add("Prahathess");
        list.add("Dhanesh");
        roomies.setItem(list);
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
    }

    @Override
    public void onClick(View view) {
        if(view == dd) {
            due_date.show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void addItemsonCat() {
        List<String> list = new ArrayList<String>();

        list.add("Cooking");
        list.add("Cleaning");
        list.add("Fun");
        list.add("Shopping");
        list.add("Walking the dog");
        list.add("Pay Bills");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat.setAdapter(dataAdapter);
    }

    private void findViewById() {
        dd=(EditText) findViewById(R.id.dd);
        dd.setInputType(InputType.TYPE_NULL);
        dd.requestFocus();
    }
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
}
