package com.example.prahathessrengasamy.roomie;

import java.io.Serializable;

/**
 * Created by prahathessrengasamy on 3/25/17.
 */

public class shoppinglist  implements Serializable{

    String product,who;
    float price,qty;

    shoppinglist(){}

    shoppinglist(String product,float price, String who, String qty) {

        this.price=price*Integer.parseInt(qty);
        this.qty=Integer.parseInt(qty);
        this.who=who;
        this.product=product;


    }
}
