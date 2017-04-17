package com.example.prahathessrengasamy.roomie;

import java.io.Serializable;

class rfPerson implements Serializable{
    public String Name;
    public String Age;
    public String f_pref;
    public String l_pref;
    public String score;

    public String liq_pref;
    public String s_pref;
    public String m_pref;
    public float compat;
    public float tscore;

    rfPerson(){}


    rfPerson(String name, String score,String l_pref,String age,String f_pref,String s_pref,String liq_pref,String m_pref) {
        this.Name = name;
        this.Age = age;
        this.f_pref=f_pref;
        this.s_pref=s_pref;
        this.liq_pref=liq_pref;
        this.l_pref=l_pref;
        this.score=score;
        this.compat=0;
        this.tscore=0;

        this.m_pref=m_pref;


    }
}