package com.example.prahathessrengasamy.roomie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class rfuserprofile extends AppCompatActivity {

    private TextView Name, Age, f_pref,score,comp,totsc,s_pref,liq_pref,m_pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rfactivity_userprofile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Name = (TextView) findViewById(R.id.n_value);
        Age = (TextView) findViewById(R.id.a_value);
        f_pref = (TextView) findViewById(R.id.f_value);
        s_pref = (TextView) findViewById(R.id.s_value);
        liq_pref = (TextView) findViewById(R.id.liq_value);
        m_pref = (TextView) findViewById(R.id.m_value);

        score = (TextView) findViewById(R.id.score);
        comp = (TextView) findViewById(R.id.comp);
        totsc = (TextView) findViewById(R.id.totsc);



        Bundle extras=getIntent().getBundleExtra("val");
        rfPerson p = (rfPerson) extras.getSerializable("values");
        Name.setText(p.Name);
        Age.setText(p.Age);
        f_pref.setText(p.f_pref);
        s_pref.setText(p.s_pref);
        liq_pref.setText(p.liq_pref);
        m_pref.setText(p.m_pref);
        score.setText(p.score);

        comp.setText(String.valueOf(p.compat)+" %");

        totsc.setText(String.valueOf(p.tscore)+" %");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2)
        {
            Name.setText(data.getStringExtra("name"));
            Age.setText(data.getStringExtra("age"));
            f_pref.setText(data.getStringExtra("fpref"));
            s_pref.setText(data.getStringExtra("spref"));
            liq_pref.setText(data.getStringExtra("liqpref"));
            m_pref.setText(data.getStringExtra("mpref"));
        }
    }
}
