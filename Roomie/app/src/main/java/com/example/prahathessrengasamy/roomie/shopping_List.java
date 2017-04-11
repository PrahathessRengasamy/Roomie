package com.example.prahathessrengasamy.roomie;

import android.app.Activity;
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

public class shopping_List extends Activity {

    private ArrayList<shoppinglist> items;
    private shopAdapter itemsAdapter;
    private ListView lvItems;
    private FloatingActionButton fab,save;
    private DatabaseReference mDatabase;
    private   Tasks t;
    private String uuid;
    private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
         uuid = intent.getStringExtra("uuid");
       t= (Tasks) getIntent().getSerializableExtra("task");
        items= new ArrayList<shoppinglist>();

     /*   mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roomie-27bba.firebaseio.com/");
        mDatabase.child("shopping/"+uuid+"/").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    items.add(child.getValue(shoppinglist.class));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        setContentView(R.layout.activity_shopping__list);
        lvItems = (ListView) findViewById(R.id.shoplist);
        fab = (FloatingActionButton) findViewById(R.id.addfab);
        save=(FloatingActionButton)findViewById(R.id.save);
        itemsAdapter = new shopAdapter(this,items);
        lvItems.setAdapter(itemsAdapter);
        addListenerOnButton();

    }

    public void addListenerOnButton() {

        final Context context = this;
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                Intent returnIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("res", items);
                returnIntent.putExtra("result",bundle);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();


        }});
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
                list.add("Chao Wang");
                list.add("Prahathess");

                spin.setItem(list);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        //mDatabase = FirebaseDatabase.getInstance().getReference();
                                        shoppinglist p = new shoppinglist(product.getText().toString(),5,spin.getSelectedItemsAsString(),quantity.getText().toString());
                                        Toast t= Toast.makeText(getApplicationContext(),"Item Added",Toast.LENGTH_LONG);
                                        t.show();
                                        items.add(p);

                                     //   mDatabase.child("tasks").child(uuid).setValue(p);
                                      //  mDatabase.child("shopping/"+uuid).child("/items"+count).setValue(p);
                                       // count++;
                                      //shopping_List.super.recreate();

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
