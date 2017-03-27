package com.example.prahathessrengasamy.roomie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



/**
 * Created by prahathessrengasamy on 3/24/17.
 */

public class shopAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<shoppinglist> mDataSource;
    public shopAdapter() {

    }
    public shopAdapter(Context context, ArrayList<shoppinglist> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.shopitem, parent, false);
        shoppinglist sl = (shoppinglist) getItem(i);
        TextView item=(TextView)rowView.findViewById(R.id.item);
        TextView person=(TextView)rowView.findViewById(R.id.person);
        TextView qty=(TextView)rowView.findViewById(R.id.quantity);
      //  TextView price=(TextView)rowView.findViewById(R.id.price);
        item.setText(sl.product);
        person.setText(sl.who);
        qty.setText(""+sl.qty);
       // price.setText(sl.Duedate);
        return rowView;
    }
}
