package com.example.prahathessrengasamy.roomie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class opening extends AppCompatActivity implements View.OnClickListener {
    private FloatingActionButton taskfab,homefab,setfab,usersfab,rf1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Context c=this;
        taskfab = (FloatingActionButton) findViewById(R.id.taskfab);
        taskfab.setOnClickListener(this);
        usersfab = (FloatingActionButton) findViewById(R.id.usersfab);
        usersfab.setOnClickListener(this);
        setfab =(FloatingActionButton) findViewById(R.id.setfab);
        setfab.setOnClickListener(this);
        homefab= (FloatingActionButton) findViewById(R.id.homefab);
        homefab.setOnClickListener(this);
        rf1= (FloatingActionButton) findViewById(R.id.rf1);
        rf1.setOnClickListener(this);
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
