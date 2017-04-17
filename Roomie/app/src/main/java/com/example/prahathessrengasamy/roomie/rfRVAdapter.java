package com.example.prahathessrengasamy.roomie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class rfRVAdapter extends RecyclerView.Adapter<rfRVAdapter.PersonViewHolder> {
    static ArrayList<rfPerson> persons;
    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView personName;
        TextView personscore;
        TextView personcomp;
        TextView persontt;


        PersonViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personscore = (TextView)itemView.findViewById(R.id.person_score);
            personcomp = (TextView)itemView.findViewById(R.id.person_comp);
            persontt = (TextView)itemView.findViewById(R.id.totalsc);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent i = new Intent(itemView.getContext(), rfuserprofile.class);
                    Bundle bundle= new Bundle();
                    bundle.putSerializable("values",persons.get(pos));
                    i.putExtra("val",bundle);
                    itemView.getContext().startActivity(i);
                    Toast.makeText(itemView.getContext(),  persons.get(pos).Name, Toast.LENGTH_SHORT).show();

                }
            });

        }
    }



    rfRVAdapter(ArrayList<rfPerson> persons){
        this.persons = persons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rfitem, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).Name);
        personViewHolder.personscore.setText("Task Score : "+persons.get(i).score);
        personViewHolder.personcomp.setText("Compatability : "+String.valueOf(persons.get(i).compat)+" %");
        personViewHolder.persontt.setText("Total : "+String.valueOf(persons.get(i).tscore)+" %");


    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}