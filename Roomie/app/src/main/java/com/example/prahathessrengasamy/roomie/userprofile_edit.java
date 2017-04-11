package com.example.prahathessrengasamy.roomie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Llewellyn on 3/26/17.
 */

public class userprofile_edit extends AppCompatActivity implements View.OnClickListener{

    private EditText Name,Age,f_pref,l_pref,liq_pref,s_pref,m_pref;
    private Button save;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        setContentView(R.layout.activity_userprofile_edit);
        Name = (EditText) findViewById(R.id.n_value);
        Age = (EditText) findViewById(R.id.a_value);
        f_pref = (EditText) findViewById(R.id.f_value);
        l_pref = (EditText) findViewById(R.id.l_value);
        s_pref = (EditText) findViewById(R.id.s_value);
        liq_pref = (EditText) findViewById(R.id.liq_value);
        m_pref = (EditText) findViewById(R.id.m_value);
        save=(Button) findViewById(R.id.save);
        Name.setText(intent.getStringExtra("name"));
        Age.setText(intent.getStringExtra("age"));
        f_pref.setText(intent.getStringExtra("f_pref"));
        l_pref.setText(intent.getStringExtra("l_pref"));
        s_pref.setText(intent.getStringExtra("s_pref"));
        liq_pref.setText(intent.getStringExtra("liq_pref"));
        m_pref.setText(intent.getStringExtra("m_pref"));
        save.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.equals(save))
        {
            String name, age, fpref,lpref,spref,liqpref,mpref;
            name=Name.getText().toString();
            age=Age.getText().toString();
            fpref=f_pref.getText().toString();
            lpref=l_pref.getText().toString();
            spref=s_pref.getText().toString();
            liqpref=liq_pref.getText().toString();
            mpref=m_pref.getText().toString();
            mDatabase = FirebaseDatabase.getInstance().getReference();
            Person person= new Person(name,age,fpref,lpref,spref,liqpref,mpref,"5");
            Toast t= Toast.makeText(getApplicationContext(),"User updated",Toast.LENGTH_LONG);
            t.show();
            mDatabase.child("users").child(name).setValue(person);
            Intent myIntent=new Intent(userprofile_edit.this,userprofile.class);
            myIntent.putExtra("name",name);
            myIntent.putExtra("age",age);
            myIntent.putExtra("fpref",fpref);
            myIntent.putExtra("lpref",lpref);
            myIntent.putExtra("spref",spref);
            myIntent.putExtra("liqpref",liqpref);
            myIntent.putExtra("mpref",mpref);
            setResult(2,myIntent);
            finish();
            //userprofile_edit.this.startActivity(myIntent);
        }
    }
}
