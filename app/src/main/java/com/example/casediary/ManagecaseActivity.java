package com.example.casediary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagecaseActivity extends AppCompatActivity {

    private Button btnaddcase,btnshowcase,btneditcase,btndeletecase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managecase);

        btnaddcase=(Button)findViewById(R.id.btnaddcase);
        btndeletecase=(Button)findViewById(R.id.btndeletecase);
        btneditcase=(Button)findViewById(R.id.btneditcase);
        btnshowcase=(Button)findViewById(R.id.btnshowcase);


        btnaddcase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),add_case.class));
            }
        });

        btnshowcase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),showcase.class));
            }
        });

        btneditcase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),showcase.class));
            }
        });

        btndeletecase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),showcase.class));
            }
        });
    }
}
