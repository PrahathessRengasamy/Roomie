package com.example.prahathessrengasamy.roomie;

import java.io.Serializable;

/**
 * Created by prahathessrengasamy on 3/25/17.
 */

public class shoppinglist  {

    String title,Creator,Desc,Category,Duedate,Workforce,Assignedto,Repeats,Status,product,who;
    float Priority,Credits,price,qty;

    shoppinglist(){}

    shoppinglist(String title,String Desc,String Category,String Duedate,String Workforce,float Priority,String product,float price, String who, String qty) {
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
        this.Assignedto=Workforce;
        this.price=price*Integer.parseInt(qty);
        this.qty=Integer.parseInt(qty);
        this.who=who;
        this.product=product;


    }
}
