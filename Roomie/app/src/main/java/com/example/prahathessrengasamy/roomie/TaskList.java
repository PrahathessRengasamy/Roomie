package com.example.prahathessrengasamy.roomie;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Llewellyn on 3/4/17.
 */

public class TaskList extends Activity {
    private  ArrayList<Tasks> items;
    private TasksListAdapter itemsAdapter;
    private ListView lvItems;
    private FloatingActionButton fab;
    private DatabaseReference mDatabase;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items=new ArrayList<Tasks>();
        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roomie-27bba.firebaseio.com/");
        mDatabase.child("tasks").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    items.add(child.getValue(Tasks.class));


                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        setContentView(R.layout.task_list);
        lvItems = (ListView) findViewById(R.id.list);
        fab = (FloatingActionButton) findViewById(R.id.homefab);
        itemsAdapter = new TasksListAdapter(this,items);
        lvItems.setAdapter(itemsAdapter);
        addListenerOnButton();


    }

    public void addListenerOnButton() {

        final Context context = this;

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, CreateTask.class);
                startActivity(intent);

            }

        });
    }


}
