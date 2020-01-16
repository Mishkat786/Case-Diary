package com.example.casediary;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class add_case extends AppCompatActivity {


    private EditText txtclientname,txtcasenumber,txtcasetitle,txtcourtno,txtcased,txtdate;
    private Button btncasesave,btncasereset;
    DatabaseReference databaseCase;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_case);

        databaseCase = FirebaseDatabase.getInstance().getReference("Case");

        txtcasenumber=(EditText)findViewById(R.id.txtcasenumber);
        txtcasetitle = (EditText)findViewById(R.id.txtcasetitle);
        txtclientname = (EditText)findViewById(R.id.txtclientname);
        txtcourtno=(EditText)findViewById(R.id.txtcourtno);
        txtcased=(EditText)findViewById(R.id.txtcased);
        txtdate=(EditText)findViewById(R.id.txtdate);
        btncasesave=(Button)findViewById(R.id.btncasesave);
        btncasereset=(Button)findViewById(R.id.btncasereset);

        btncasesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addcase();
            }
        });
        btncasereset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtcased.setText("");
                txtcasenumber.setText("");
                txtcasetitle.setText("");
                txtcourtno.setText("");
                txtdate.setText("");
                txtclientname.setText("");
            }
        });


        txtdate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    Calendar calendar= Calendar.getInstance();
                }
                final int year = Calendar.YEAR;
                final int month = Calendar.MONTH;
                final int day = Calendar.DAY_OF_MONTH;

                datePickerDialog = new DatePickerDialog(add_case.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        txtdate.setText(day+"-"+month+"-"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }


    public void addcase(){

        String casenumber=txtcasenumber.getText().toString().trim();
        String clientname = txtclientname.getText().toString().trim();
        String casetitle=txtcasetitle.getText().toString().trim();
        String courtno=txtcourtno.getText().toString().trim();
        String cased=txtcased.getText().toString().trim();
        String date=txtdate.getText().toString().trim();


        if (!TextUtils.isEmpty(casenumber)){

            String caseid = databaseCase.push().getKey();

            AddCase addCase = new AddCase(caseid,clientname,casenumber,casetitle,courtno,cased,date);
            databaseCase.child(caseid).setValue(addCase);

            Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Enter case number",Toast.LENGTH_LONG).show();
        }


        if(TextUtils.isEmpty(clientname)){

            Toast.makeText(add_case.this,"Please enter Client Name",Toast.LENGTH_LONG).show();

        }

        if(TextUtils.isEmpty(casetitle)){

            Toast.makeText(add_case.this,"Please enter Case Title",Toast.LENGTH_LONG).show();

        }

        if(TextUtils.isEmpty(cased)){

            Toast.makeText(add_case.this,"Please enter Case Description",Toast.LENGTH_LONG).show();

        }
        if(TextUtils.isEmpty(date)){

            Toast.makeText(add_case.this,"Please enter Date",Toast.LENGTH_LONG).show();

        }


    }


}
