package com.example.prahathessrengasamy.roomie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.jar.Attributes;

import static android.R.attr.data;

/**
 * Created by Llewellyn on 3/28/17.
 */

public class TaskView extends Activity {
    private TextView title,des,category,due_date,workforce,credits,creator;
    private RatingBar priority;
<<<<<<< HEAD
    private Button back,del,edit;
    private DatabaseReference mDatabase;
=======
    private Button back,del;
    DatabaseReference mDatabase;
>>>>>>> Gamification
    private Switch status;
    private Person[] persons;
    String uuid;
    int i;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_item);

        title=(TextView) findViewById(R.id.title);
        des=(TextView) findViewById(R.id.des);
        del=(Button) findViewById(R.id.del);
        category=(TextView) findViewById(R.id.cat);
        due_date=(TextView) findViewById(R.id.dd);
        workforce=(TextView) findViewById(R.id.roomies);
        priority=(RatingBar) findViewById(R.id.priority);
        credits=(TextView) findViewById(R.id.credits);
        creator=(TextView) findViewById(R.id.creator);
        back=(Button) findViewById(R.id.back);
        status=(Switch)findViewById(R.id.status);
<<<<<<< HEAD
        edit=(Button)findViewById(R.id.edit);
        Tasks item= (Tasks) getIntent().getSerializableExtra("item");
=======
        final Tasks item= (Tasks) getIntent().getSerializableExtra("item");
>>>>>>> Gamification
        uuid=item.tid;

        title.setText(item.title);
        des.setText(item.Desc);
        category.setText(item.Category);
        due_date.setText(item.Duedate);
        workforce.setText(item.Workforce);
        priority.setNumStars(5);
        priority.setRating(item.Priority);
        credits.setText("" + (item.Credits));
        creator.setText(item.Creator);
        status.setChecked((item.Status.contains("open"))?true:false);
        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roomie-27bba.firebaseio.com/");

        final String[] array = item.Assignedto.split(",");
        persons=new Person[array.length];
        if(array.length==1){
            mDatabase.child("users").child(array[0]).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    persons[i] = snapshot.getValue(Person.class);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.print("@@@@@@");
                }
            });
        }
        else{
            for ( i = 0; i < array.length; i++) {
                mDatabase.child("users").child(array[0]).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        persons[i] = snapshot.getValue(Person.class);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.print("@@@@@@");
                    }
                });
            }
        }

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!status.isChecked()){

                    mDatabase.child("tasks").child(item.tid).child("Status").setValue("Closed");





                    for (int i = 0; i < array.length; i++) {
                        mDatabase.child("users").child(array[i]).child("score").setValue(""+(Float.parseFloat(persons[i].score)+item.Credits));
                    }
                }
                else {
                    mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roomie-27bba.firebaseio.com/");
                    mDatabase.child("tasks").child(item.tid).child("Status").setValue("open");
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED, new Intent(TaskView.this, TaskList.class));
                finish();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("tasks").child(uuid).removeValue();
                Toast t = Toast.makeText(getApplicationContext(), "Deleted Task", Toast.LENGTH_LONG);
                t.show();
                setResult(Activity.RESULT_OK, new Intent(TaskView.this, TaskList.class));
                finish();
            }
        });

      edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(TaskView.this, TaskEdit.class);
                myintent.putExtra("title", title.getText().toString());
                myintent.putExtra("des", des.getText().toString());
                myintent.putExtra("tid", uuid);
                myintent.putExtra("cat", category.getText().toString());
                myintent.putExtra("dd", due_date.getText().toString());
                myintent.putExtra("workforce", workforce.getText().toString());
                myintent.putExtra("priority", priority.getNumStars());
                TaskView.this.startActivity(myintent);
            }
        });
    }
}
