package com.example.prahathessrengasamy.roomie;

import java.io.Serializable;

/**
 * Created by prahathessrengasamy on 3/5/17.
 */

public class udet  implements Serializable{

    String tokenn,Owner;
    udet(){}
    udet(String tokenn) {
        this.Owner = "Mrun";
        this.tokenn=tokenn;

    }
}
