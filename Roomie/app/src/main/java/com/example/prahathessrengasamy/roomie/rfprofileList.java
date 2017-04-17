package com.example.prahathessrengasamy.roomie;

/**
 * Created by mrunmayisamant on 4/16/17.
 */

        import android.app.Activity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.View;
        import android.widget.Toast;


        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;
        import java.util.*;
        import java.util.Locale;

public class rfprofileList extends Activity {
    private DatabaseReference mDatabase;
    private ArrayList<Person> cuser;
    private ArrayList<rfPerson> persons;
    private float maxx;

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.rfprofilelist);


       // getActionBar().setTitle("Test44343434");


        rv=(RecyclerView)findViewById(R.id.rv);
        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roomie-27bba.firebaseio.com/");
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);



        cuser = new ArrayList<>();
        mDatabase.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    cuser.add(child.getValue(Person.class));
                }

                System.out.println("cuserrr"+cuser.get(0).Name);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






        //initializeData1();

        initializeData();
        //initializeAdapter();

    }

    private void initializeData(){
        persons = new ArrayList<>();
        mDatabase.child("all_user").orderByChild("score").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    persons.add(child.getValue(rfPerson.class));
                    maxx=Float.parseFloat(persons.get(0).score);
                    System.out.println("persons maxx"+maxx);
                }
                initializeAdapter();





            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       // cuser = new ArrayList<>();




    }

    private void initializeData1(){
        cuser = new ArrayList<>();
        mDatabase.child("users").child("Vishal").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    cuser.add(child.getValue(Person.class));
                }

                //System.out.println("cuserrr"+cuser.get(0).Name);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // cuser = new ArrayList<>();




    }







    private void initializeAdapter(){
float nn,n2;

        int induser=2;
        System.out.println("cuserrrrrr"+cuser.get(induser).Name);
        for(int i=0;i<persons.size();i++)
        {System.out.println("i 88888888****       :"+i);
            if (Math.abs(Integer.parseInt(cuser.get(induser).Age)-Integer.parseInt(persons.get(i).Age))<=5)
            {persons.get(i).compat+=10; System.out.println("Age");}
            else if (Math.abs(Integer.parseInt(cuser.get(induser).Age)-Integer.parseInt(persons.get(i).Age))<=10)
            {persons.get(i).compat+=5; System.out.println("Age");}
            else if (Math.abs(Integer.parseInt(cuser.get(induser).Age)-Integer.parseInt(persons.get(i).Age))<=15)
            {persons.get(i).compat+=2.5; System.out.println("Age");}




            if (cuser.get(induser).f_pref.equals(persons.get(i).f_pref))
            {persons.get(i).compat+=5;System.out.println("fff");}
            if (cuser.get(induser).s_pref.equals(persons.get(i).s_pref))
            {persons.get(i).compat+=10;System.out.println("s");}
            if (cuser.get(induser).liq_pref.equals(persons.get(i).liq_pref))
            {persons.get(i).compat+=5;System.out.println("liq");}
            if (cuser.get(induser).m_pref.equals(persons.get(i).m_pref))
            {persons.get(i).compat+=10;System.out.println("gen");}



            System.out.println("persons maxx"+maxx);

            n2=persons.get(i).compat*60/40;//60 % of total score
            persons.get(i).compat=persons.get(i).compat*100/40;
            nn= Float.parseFloat(persons.get(i).score)*40/maxx;
            persons.get(i).tscore+=n2+nn;
            System.out.println("persons ccc"+persons.get(i).tscore);


        }


        Collections.sort(persons, new Comparator<rfPerson>() {
            @Override public int compare(rfPerson p1, rfPerson p2) {
                if (p2.tscore<p1.tscore){return -1;}
                else return 1;
            }

        });

        System.out.println("persons ccc0000"+persons.get(0).tscore+"fff"+persons.get(0).compat);
        System.out.println("persons ccc1111"+persons.get(1).tscore+"fff"+persons.get(1).compat);

        rfRVAdapter adapter = new rfRVAdapter(persons);
        rv.setAdapter(adapter);
    }
}