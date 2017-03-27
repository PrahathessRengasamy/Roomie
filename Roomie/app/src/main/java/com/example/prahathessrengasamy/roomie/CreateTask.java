package com.example.prahathessrengasamy.roomie;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

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
    private DatabaseReference mDatabase;
    String uuid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task);
        sdf=new SimpleDateFormat("MM-dd-yyyy", Locale.US);
        findViewsById();
        setDateTimeField();
        roomies = (spinAdapter) findViewById(R.id.roomies);
        cat = (Spinner) findViewById(R.id.cat);
        submit = (Button) findViewById(R.id.submit);
        title = (EditText) findViewById(R.id.title);
        des = (EditText) findViewById(R.id.des);
        list = (Button) findViewById(R.id.list);
        priority = (RatingBar) findViewById(R.id.priority);
        addItemsonCat();
        addItemsonRoomies();
       // String uuid =UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        genuuid();


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
            public void onClick(View v) {
                // Perform action on click
                mDatabase = FirebaseDatabase.getInstance().getReference();
                Tasks p = new Tasks(title.getText().toString(),des.getText().toString(),cat.getSelectedItem().toString(),dd.getText().toString(),roomies.getSelectedItem().toString(),priority.getRating());
               Toast t= Toast.makeText(getApplicationContext(),"Task Added",Toast.LENGTH_LONG);
                t.show();
                mDatabase.child("tasks").child(uuid).setValue(p);
                CreateTask.super.onBackPressed();


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
                Tasks p = new Tasks(title.getText().toString(),des.getText().toString(),cat.getSelectedItem().toString(),dd.getText().toString(),roomies.getSelectedItem().toString(),priority.getRating());

                Intent intent = new Intent(getApplicationContext(), shopping_List.class);
                intent.putExtra("uuid",uuid);
                intent.putExtra("task",p);
                startActivity(intent);


            }
        });
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

}
