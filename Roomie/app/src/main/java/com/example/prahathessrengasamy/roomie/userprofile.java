package com.example.prahathessrengasamy.roomie;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class userprofile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView Name = (TextView) findViewById(R.id.textView12);
        TextView Age = (TextView) findViewById(R.id.textView13);
        TextView pref = (TextView) findViewById(R.id.textView3);
        Name.setText(getIntent().getStringExtra("Name"));
        Age.setText(getIntent().getStringExtra("Age"));
        pref.setText(getIntent().getStringExtra("pref"));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.homefab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
