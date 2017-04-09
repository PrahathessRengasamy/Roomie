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

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {
    static ArrayList<Person> persons;
    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView pho;

        PersonViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            pho=(ImageView)itemView.findViewById(R.id.imageView5);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent i = new Intent(itemView.getContext(), userprofile.class);
                    Bundle bundle= new Bundle();
                    bundle.putSerializable("values",persons.get(pos));
                    i.putExtra("val",bundle);
                    itemView.getContext().startActivity(i);
                    Toast.makeText(itemView.getContext(),  persons.get(pos).Name, Toast.LENGTH_SHORT).show();

                }
            });

        }
    }



    RVAdapter(ArrayList<Person> persons){
        this.persons = persons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).Name);
        personViewHolder.personAge.setText(persons.get(i).Age);
        personViewHolder.pho.setImageResource(R.drawable.cooking);

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}