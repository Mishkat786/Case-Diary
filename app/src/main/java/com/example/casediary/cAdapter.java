package com.example.casediary;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class cAdapter extends ArrayAdapter<addclient> {

    private Activity context;
    private List<addclient> addclientList;

    public cAdapter(Activity context,List<addclient> addclientList) {
        super(context, R.layout.c_showlayout, addclientList);
        this.context = context;
        this.addclientList = addclientList;
    }



    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
       View view= layoutInflater.inflate(R.layout.c_showlayout,null,true);
       addclient addclient= addclientList.get(position);

        TextView t1 = view.findViewById(R.id.textname);
        TextView t2 = view.findViewById(R.id.textad);
        TextView t3 = view.findViewById(R.id.textcity);
        TextView t4 = view.findViewById(R.id.textzip);
        TextView t5 = view.findViewById(R.id.textnum);

        t1.setText(addclient.getClientname());
        t2.setText(addclient.getAddress());
        t3.setText(addclient.getCity());
        t4.setText(addclient.getZip());
        t5.setText(addclient.getNumber());

        return view;
    }
}
