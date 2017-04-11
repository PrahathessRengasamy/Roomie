package com.example.prahathessrengasamy.roomie;

import java.io.Serializable;

class Person implements Serializable{
    public String Name;
    public String Age;
    public String f_pref;
    public String l_pref;
    public String s_pref;
    public String liq_pref;
    public String m_pref;
    public String score;

    Person(){}
    Person(String name, String age,String f_pref,String l_pref,String s_pref,String liq_pref,String m_pref,String score) {
        this.Name = name;
        this.Age = age;
        this.f_pref=f_pref;
        this.l_pref=l_pref;
        this.s_pref=s_pref;
        this.liq_pref=liq_pref;
        this.m_pref=m_pref;
        this.score=score;


    }

}