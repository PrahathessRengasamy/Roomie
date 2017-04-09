package com.example.prahathessrengasamy.roomie;

/**
 * Created by prahathessrengasamy on 3/5/17.
 */

public class Shopping {

    String title,Creator,Desc,Category,Duedate,Workforce,Assignedto,Repeats,Status;
    float Priority,Credits;
    Shopping(){}
    Shopping(String title,String Desc,String Category,String Duedate,String Workforce,float Priority) {
        this.title = title;
        this.Creator = "Prahathess";
        this.Desc=Desc;
        this.Category=Category;
        this.Duedate=Duedate;
        this.Workforce=Workforce;
        this.Priority=Priority;
        this.Repeats="No";
        this.Status="open";
        this.Credits=Priority*4;
        this.Assignedto="Vishal";
    }
}
