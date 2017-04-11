package com.example.prahathessrengasamy.roomie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class userprofile extends AppCompatActivity {

    private TextView Name, Age, f_pref,l_pref,s_pref,liq_pref,m_pref,score;
    private AdView mAdView;
    private Button btnFullscreenAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Name = (TextView) findViewById(R.id.n_value);
        Age = (TextView) findViewById(R.id.a_value);
        f_pref = (TextView) findViewById(R.id.f_value);
        l_pref = (TextView) findViewById(R.id.l_value);
        s_pref = (TextView) findViewById(R.id.s_value);
        liq_pref = (TextView) findViewById(R.id.liq_value);
        m_pref = (TextView) findViewById(R.id.m_value);
        score = (TextView) findViewById(R.id.points);
        Bundle extras=getIntent().getBundleExtra("val");
        Person p = (Person) extras.getSerializable("values");
        Name.setText(p.Name);
        Age.setText(p.Age);
        f_pref.setText(p.f_pref);
        l_pref.setText(p.l_pref);
        s_pref.setText(p.s_pref);
        liq_pref.setText(p.liq_pref);
        m_pref.setText(p.m_pref);
        score.setText(p.score);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.homefab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(userprofile.this,userprofile_edit.class);
                myIntent.putExtra("name",Name.getText().toString());
                myIntent.putExtra("age",Age.getText().toString());
                myIntent.putExtra("f_pref",f_pref.getText().toString());
                myIntent.putExtra("l_pref",l_pref.getText().toString());
                myIntent.putExtra("s_pref",s_pref.getText().toString());
                myIntent.putExtra("liq_pref",liq_pref.getText().toString());
                myIntent.putExtra("m_pref",m_pref.getText().toString());
                userprofile.this.startActivityForResult(myIntent,2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2)
        {
            Name.setText(data.getStringExtra("name"));
            Age.setText(data.getStringExtra("age"));
            f_pref.setText(data.getStringExtra("fpref"));
            l_pref.setText(data.getStringExtra("lpref"));
            s_pref.setText(data.getStringExtra("spref"));
            liq_pref.setText(data.getStringExtra("liqpref"));
            m_pref.setText(data.getStringExtra("mpref"));
        }
    }
}
