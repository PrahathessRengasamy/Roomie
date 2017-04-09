package com.example.prahathessrengasamy.roomie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class myTaskAdapter extends RecyclerView.Adapter<myTaskAdapter.mytaskViewholder> {
    static ArrayList<Tasks> task;
    public static class mytaskViewholder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView tName;
        TextView due;
        TextView person;
        ImageView icons;

        mytaskViewholder(final View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            tName = (TextView)itemView.findViewById(R.id.tname);
            due = (TextView)itemView.findViewById(R.id.due);
            person=(TextView)itemView.findViewById(R.id.user);
            icons=(ImageView)itemView.findViewById(R.id.imageView3);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent i = new Intent(itemView.getContext(), TaskView.class);

                    i.putExtra("item",task.get(pos));
                    itemView.getContext().startActivity(i);
                    Toast.makeText(itemView.getContext(),  task.get(pos).title, Toast.LENGTH_SHORT).show();

                }
            });

        }
    }



    myTaskAdapter(ArrayList<Tasks> task){
        this.task = task;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public mytaskViewholder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mytasks, viewGroup, false);
        mytaskViewholder pvh = new mytaskViewholder(v);
        return pvh;
    }


    @Override
    public void onBindViewHolder(mytaskViewholder mytaskViewholder, int i) {
        mytaskViewholder.tName.setText(task.get(i).title);
        mytaskViewholder.due.setText(task.get(i).Duedate);
        mytaskViewholder.person.setText(task.get(i).Workforce);
        switch(task.get(i).Category){
            case "Shopping":
                mytaskViewholder.icons.setImageResource(R.drawable.shopping);
                break;
            case "Cooking":
                mytaskViewholder.icons.setImageResource(R.drawable.cooking);
                break;
            case "Cleaning":
                mytaskViewholder.icons.setImageResource(R.drawable.cleaning);
                break;
            case "Fun":
                mytaskViewholder.icons.setImageResource(R.drawable.fun);
                break;

        }

    }

    @Override
    public int getItemCount() {
        return task.size();
    }
}