package com.example.prahathessrengasamy.roomie;

import java.io.Serializable;

/**
 * Created by prahathessrengasamy on 3/5/17.
 */

public class Tasks  implements Serializable{

    public String title,Creator,Desc,Category,Duedate,Workforce,Assignedto,Repeats,Status,tid;
    float Priority,Credits;
    Tasks(){}
    Tasks(String title,String Desc,String Category,String Duedate,String Workforce,float Priority, String tid,String status) {
        this.title = title;
        this.Creator = "Prahathess";
        this.tid=tid;
        this.Desc=Desc;
        this.Category=Category;
        this.Duedate=Duedate;
        this.Workforce=Workforce;
        this.Priority=Priority;
        this.Repeats="No";
        this.Status=status;
        this.Credits=Priority*4;
        this.Assignedto=Workforce;
    }
}
