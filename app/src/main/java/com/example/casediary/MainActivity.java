package com.example.casediary;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private Button btnmanage,btnmanage_case,btndate,btnupdate,btnmain,btnlaw,signout;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnmanage = (Button) findViewById(R.id.btnmanage);
        btnmanage_case = (Button) findViewById(R.id.btnmanage_case);
        btndate = (Button) findViewById(R.id.btndate);
        btnupdate = (Button) findViewById(R.id.btnupdate);
        btnmain = (Button) findViewById(R.id.btnmain);
        btnlaw = (Button) findViewById(R.id.btnlaw);
        signout=(Button)findViewById(R.id.signout);
        firebaseAuth=FirebaseAuth.getInstance();


        btnmanage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManageclientActivity.class);
                startActivity(intent);
            }
        });

        btnmanage_case = (Button) findViewById(R.id.btnmanage_case);
        btnmanage_case.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManagecaseActivity.class);
                startActivity(intent);
            }
        });

        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnlaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LawsActivity.class);
                startActivity(intent);

            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),showcase.class));
            }
        });

        btnmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),search.class));
            }
        });




        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                Toast.makeText(MainActivity.this,"Sign Out",Toast.LENGTH_LONG).show();
            }
        });


        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Notification.class));
            }
        });
    }


}

