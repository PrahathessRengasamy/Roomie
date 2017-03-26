package com.example.prahathessrengasamy.roomie;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class shopping_List extends AppCompatActivity {

    private ArrayList<shoppinglist> items;
    private shopAdapter itemsAdapter;
    private ListView lvItems;
    private FloatingActionButton fab;
    private DatabaseReference mDatabase;
    private   Tasks t;
    private String uuid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
         uuid = intent.getStringExtra("uuid");
       t= (Tasks) getIntent().getSerializableExtra("task");
        items=new ArrayList<shoppinglist>();

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roomie-27bba.firebaseio.com/");
        mDatabase.child("shopping").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    items.add(child.getValue(shoppinglist.class));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        setContentView(R.layout.activity_shopping__list);
        lvItems = (ListView) findViewById(R.id.shoplist);
        fab = (FloatingActionButton) findViewById(R.id.addfab);
        itemsAdapter = new shopAdapter(this,items);
        lvItems.setAdapter(itemsAdapter);
        addListenerOnButton();



    }

    public void addListenerOnButton() {

        final Context context = this;

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.popup, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText product = (EditText) promptsView
                        .findViewById(R.id.pdt);
                final EditText quantity = (EditText) promptsView
                        .findViewById(R.id.q);
                final spinAdapter spin; spin = (spinAdapter)promptsView.findViewById(R.id.roommates);
                List<String> list = new ArrayList<String>();
                list.add("Vishal");
                list.add("Mohit");
                list.add("Prahathess");
                list.add("Dhanesh");
                spin.setItem(list);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        mDatabase = FirebaseDatabase.getInstance().getReference();
                                        shoppinglist p = new shoppinglist(t.title,t.Desc,t.Category,t.Duedate,t.Workforce,t.Priority,product.getText().toString(),5,spin.getSelectedItemsAsString(),quantity.getText().toString());

                                        Toast t= Toast.makeText(getApplicationContext(),"Task Added",Toast.LENGTH_LONG);
                                        t.show();
                                        mDatabase.child("tasks").child(uuid).setValue(p);
                                        mDatabase.child("shopping").child(uuid).setValue(p);
                                        //shopping_List.super.onBackPressed();

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();


            }

        });
    }
}
