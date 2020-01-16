package com.example.casediary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManageclientActivity extends AppCompatActivity {

    private Button btnedit,btnshow,btnremove,btnadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageclient);

        btnadd=(Button)findViewById(R.id.btnadd);
        btnedit=(Button)findViewById(R.id.btnedit);
        btnremove=(Button)findViewById(R.id.btnremove);
        btnshow=(Button)findViewById(R.id.btnshow);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),add_client.class));
            }
        });

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),show_client.class));
            }
        });

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),show_client.class));
            }
        });

        btnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),show_client.class));
            }
        });
    }
}
