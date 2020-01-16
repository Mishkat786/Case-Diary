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

public class caseAdapter extends ArrayAdapter<AddCase> {


    private Activity context;
    private List<AddCase>addCaseList;

    public caseAdapter(Activity context, List<AddCase> addCaseList) {
        super(context,R.layout.sample_layout,addCaseList);
        this.context = context;
        this.addCaseList = addCaseList;
    }


    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {


        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout,null,true);

        AddCase addCase = addCaseList.get(position);

        TextView t0 = view.findViewById(R.id.textclientname);
        TextView t1= view.findViewById(R.id.textcasenumber);
        TextView t2= view.findViewById(R.id.textcasetitle);
        TextView t3= view.findViewById(R.id.textcourno);
        TextView t4= view.findViewById(R.id.textcased);
        TextView t5= view.findViewById(R.id.textdate);


        t0.setText(addCase.getClientname());
       t1.setText(addCase.getCasenumber());
       t2.setText(addCase.getCasetitle());
       t3.setText(addCase.getCourtno());
       t4.setText(addCase.getCased());
       t5.setText(addCase.getDate());


        return view ;
    }
}
