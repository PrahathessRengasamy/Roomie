package com.example.prahathessrengasamy.roomie;




import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.UUID;


public class datatrial extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {

            System.out.println("helllo1");
             DatabaseReference mDatabase;
String token,taskid;
              final String TAG = "MyFirebaseIIDService";

        //Intent intent = getIntent();
        token = intent.getStringExtra("nott");
            taskid = intent.getStringExtra("taskid");
Log.d("sgafud","ssssshgjgdd"+token);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("rem").child(genuuid()).child(token).setValue(taskid);
    }
        private String genuuid(){
String uuid;
            uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
            return uuid;
        }
}
