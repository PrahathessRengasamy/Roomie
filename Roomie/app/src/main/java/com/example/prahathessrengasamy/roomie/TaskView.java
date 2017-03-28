package com.example.prahathessrengasamy.roomie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.jar.Attributes;

/**
 * Created by Llewellyn on 3/28/17.
 */

public class TaskView extends Activity {
    private TextView title,des,category,due_date,workforce,credits,creator;
    private RatingBar priority;
    private Button back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_item);
        title=(TextView) findViewById(R.id.title);
        des=(TextView) findViewById(R.id.des);
        category=(TextView) findViewById(R.id.cat);
        due_date=(TextView) findViewById(R.id.dd);
        workforce=(TextView) findViewById(R.id.roomies);
        priority=(RatingBar) findViewById(R.id.priority);
        credits=(TextView) findViewById(R.id.credits);
        creator=(TextView) findViewById(R.id.creator);
        back=(Button) findViewById(R.id.back);
        Tasks item= (Tasks) getIntent().getSerializableExtra("item");
        title.setText(item.title);
        des.setText(item.Desc);
        category.setText(item.Category);
        due_date.setText(item.Duedate);
        workforce.setText(item.Workforce);
        priority.setNumStars((int)item.Priority);
        credits.setText(""+(item.Credits));
        creator.setText(item.Creator);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED,new Intent(TaskView.this,TaskList.class));
                finish();
            }
        });
    }
}
