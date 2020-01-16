package com.example.casediary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class LawsActivity extends AppCompatActivity {

    private static final String TAG = "LawsActivity";
    private Context mContext;
    ArrayList<String> titleArrayList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laws);

        mContext=LawsActivity.this;

        titleArrayList = new ArrayList<String >();
        titleArrayList.add(Constant.ফৌজদারি_দন্ডবিধি);
        titleArrayList.add(Constant.শিশু_আইন);
        titleArrayList.add(Constant.অর্থ_আইন_২০১৮);
        titleArrayList.add(Constant.আইন_শৃঙ্খলা_বিঘ্নকারী_অপরাধ_দ্রুত_বিচার_আইন);
        titleArrayList.add(Constant.কস্ট_এন্ড_ম্যানেজমেন্ট_অ্যাকাউন্ট্যান্টস_আইন_২০১৮);
        titleArrayList.add(Constant.দ্রুতবিচার_আদালত_আইন);
        titleArrayList.add(Constant.মাদকদ্রব্য_নিয়ন্ত্রণ_আইন_২০১৮);
        titleArrayList.add(Constant.সন্ত্রাস_বিরোধী_আইন);
        titleArrayList.add(Constant.সাংবিধানিক_আইন);


        mRecyclerView = (RecyclerView) findViewById(R.id.title_layout_recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        TitleAdapter adapter = new TitleAdapter(mContext, titleArrayList, new CustomitemClickListener() {
            @Override
            public void onItemCLick(View v, int positon) {


                Intent desIntent = new Intent(mContext,DescriptionActivity.class);
                desIntent.putExtra("titles",titleArrayList.get(positon));
                startActivity(desIntent);


                Toast.makeText(mContext,"Clicked"+titleArrayList.get(positon), Toast.LENGTH_SHORT).show();

            }
        });

        mRecyclerView.setAdapter(adapter);

    }
}
