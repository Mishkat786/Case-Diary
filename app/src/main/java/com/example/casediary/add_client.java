package com.example.casediary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_client extends AppCompatActivity {

    private EditText txtclientname,txtaddress,txtcity,txtzip,txtnumber;
    private Button btnsave,btnreset;
    DatabaseReference databaseReference;
    private addclient addclient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);


        btnreset=(Button)findViewById(R.id.btnreset);
        btnsave=(Button)findViewById(R.id.btnsave);

        txtaddress=(EditText)findViewById(R.id.txtaddress);
        txtcity=(EditText)findViewById(R.id.txtcity);
        txtclientname=(EditText)findViewById(R.id.txtclientname);
        txtnumber=(EditText)findViewById(R.id.txtnumber);
        txtzip=(EditText)findViewById(R.id.txtzip);
        addclient = new addclient();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Add Client");

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addclient();

            }
        });

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtclientname.setText("");
                txtnumber.setText("");
                txtzip.setText("");
                txtcity.setText("");
                txtaddress.setText("");
            }
        });

    }


    public  void addclient(){
        String clientname=txtclientname.getText().toString().trim();
        String address=txtaddress.getText().toString().trim();
        String city=txtcity.getText().toString().trim();
        String zip=txtzip.getText().toString().trim();
        String number=txtnumber.getText().toString().trim();
        if (!TextUtils.isEmpty(clientname)){

            String clientid = databaseReference.push().getKey();

            addclient addclient = new addclient(clientid,clientname,address,city,zip,number);
            databaseReference.child(clientid).setValue(addclient);

            Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Enter Name",Toast.LENGTH_LONG).show();
        }

        if (TextUtils.isEmpty(address)){

            Toast.makeText(add_client.this,"Please Enter Address",Toast.LENGTH_LONG).show();
            return;

        }
        if (TextUtils.isEmpty((number))){

            Toast.makeText(add_client.this,"Please Enter number",Toast.LENGTH_LONG).show();
        }

        if (TextUtils.isEmpty(city)){

            Toast.makeText(add_client.this,"Please Enter City",Toast.LENGTH_LONG).show();
        }
    }
}
