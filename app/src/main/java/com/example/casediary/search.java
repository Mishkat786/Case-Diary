package com.example.casediary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class search extends AppCompatActivity {

     private Button casesearch,clientsearch;
     private CalendarView calendarView;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        casesearch = (Button)findViewById(R.id.casesearch);
        clientsearch = (Button)findViewById(R.id.clientsearch);

        clientsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),SearchClient.class));
            }
        });
    }
}
