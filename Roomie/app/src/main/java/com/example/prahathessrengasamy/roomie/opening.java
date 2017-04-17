package com.example.prahathessrengasamy.roomie;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class opening extends AppCompatActivity implements View.OnClickListener {
<<<<<<< HEAD
    private FloatingActionButton taskfab,homefab,setfab,usersfab;
    DatabaseReference mDatabase;
    ArrayList<Tasks> task;
    Tasks mytask;
    RecyclerView rv;
    private AdView mAdView;
    private Button btnFullscreenAd;
=======
    private FloatingActionButton taskfab,homefab,setfab,usersfab,rf1;
>>>>>>> push
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancelAll();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Context c=this;
        rv=(RecyclerView)findViewById(R.id.rvw);
        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roomie-27bba.firebaseio.com/");
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        initializeData();
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        taskfab = (FloatingActionButton) findViewById(R.id.taskfab);
        taskfab.setOnClickListener(this);
        usersfab = (FloatingActionButton) findViewById(R.id.usersfab);
        usersfab.setOnClickListener(this);
        setfab =(FloatingActionButton) findViewById(R.id.setfab);
        setfab.setOnClickListener(this);
        homefab= (FloatingActionButton) findViewById(R.id.homefab);
        homefab.setOnClickListener(this);
<<<<<<< HEAD


    }
    private void initializeData() {
        task = new ArrayList<>();
         mytask = new Tasks();
        mDatabase.child("tasks").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {

                    mytask=child.getValue(Tasks.class);
                    if(mytask.Assignedto.contains((CharSequence)"Vishal")) {
                        opening.this.task.add(child.getValue(Tasks.class));
                        initializeAdapter();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
=======
        rf1= (FloatingActionButton) findViewById(R.id.rf1);
        rf1.setOnClickListener(this);
>>>>>>> push
    }
        private void initializeAdapter(){
            myTaskAdapter adapter = new myTaskAdapter(task);
            rv.setAdapter(adapter);
        }



    @Override
    public void onClick(View v) {
        if(v.equals(taskfab))
        {
            Intent intent = new Intent(opening.this,TaskList.class);
            startActivity(intent);
        }
        else if (v.equals((usersfab)))
        {
            Intent intent = new Intent(opening.this,profileList.class);
            startActivity(intent);
        }
        else if(v.equals(setfab))
        {
            //Do for settings activity
            Intent intent = new Intent(opening.this,Scan.class);
            startActivity(intent);

        }

        else if(v.equals(rf1))
        {
            //Do for settings activity
            Intent intent = new Intent(opening.this,rfprofileList.class);
            startActivity(intent);

        }
        else if(v.equals(homefab))
        {
            Intent intent = new Intent(opening.this,Scan.class);
            startActivity(intent);

        }
    }
}
