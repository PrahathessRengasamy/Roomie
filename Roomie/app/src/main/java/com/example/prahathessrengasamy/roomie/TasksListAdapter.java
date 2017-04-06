package com.example.prahathessrengasamy.roomie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



/**
 * Created by prahathessrengasamy on 3/24/17.
 */

public class TasksListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Tasks> mDataSource;

    public TasksListAdapter(Context context, ArrayList<Tasks> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.taskitem, parent, false);
        Tasks task = (Tasks) getItem(i);
        TextView tname=(TextView)rowView.findViewById(R.id.tname);
        TextView users=(TextView)rowView.findViewById(R.id.users);
        TextView due=(TextView)rowView.findViewById(R.id.due);
        ImageView icon =(ImageView)rowView.findViewById(R.id.imageView4);
        switch(task.Category){
            case "Shopping":
                icon.setImageResource(R.drawable.shopping);
                break;
            case "Cooking":
                icon.setImageResource(R.drawable.cooking);
                break;
            case "Cleaning":
                icon.setImageResource(R.drawable.cleaning);
                break;
            case "Fun":
                icon.setImageResource(R.drawable.fun);
                break;

        }
        tname.setText(task.title);
        users.setText(task.Assignedto);
        due.setText(task.Duedate);
        return rowView;
    }
}
