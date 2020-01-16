package com.example.casediary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.allyants.notifyme.NotifyMe;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class Notification extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    Calendar now = Calendar.getInstance();
    TimePickerDialog tpd;
    DatePickerDialog dpd;
    EditText textClientName, textCaseNumber;
    Button btnNotify, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        btnNotify = (Button) findViewById(R.id.btnNotify);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        textCaseNumber = (EditText) findViewById(R.id.textCaseNumber);
        textClientName = (EditText) findViewById(R.id.textClientName);


        dpd = DatePickerDialog.newInstance(
                Notification.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );


        tpd = TimePickerDialog.newInstance(
          Notification.this,
          now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                now.get(Calendar.SECOND),
                false
        );
       btnCancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              textCaseNumber.setText("");
              textClientName.setText("");
           }
       });

       btnNotify.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dpd.show(getFragmentManager(),"DatePickerDialog");

               Toast.makeText(Notification.this,"You Will Notify! Thank you.",Toast.LENGTH_LONG).show();
           }
       });

    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        now.set(Calendar.YEAR,year);
        now.set(Calendar.MONTH,monthOfYear);
        now.set(Calendar.DAY_OF_MONTH,dayOfMonth);

      tpd.show(getFragmentManager(),"TimePickerDialog");
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

        now.set(Calendar.HOUR_OF_DAY,hourOfDay);
        now.set(Calendar.MINUTE,minute);
        now.set(Calendar.SECOND,second);

        NotifyMe notifyMe = new NotifyMe.Builder(getApplicationContext())
                .title(textCaseNumber.getText().toString())
                .content(textClientName.getText().toString())
                .color(255,0,0,255)
                .time(now)
                .addAction(new Intent(),"Snooze",false)
                .key("test")
                .addAction(new Intent(),"Dismiss",true,false)
                .addAction(new Intent(),"Done")
                .large_icon(R.mipmap.ic_launcher_round)
                .build();
    }
}


