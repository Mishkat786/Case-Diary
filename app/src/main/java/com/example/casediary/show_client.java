package com.example.casediary;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class show_client extends AppCompatActivity {

    private ListView listshow;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private List<addclient> addclientList;
    private cAdapter cAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_client);

      databaseReference=FirebaseDatabase.getInstance().getReference("Add Client");
      addclientList=new ArrayList<>();
      cAdapter= new cAdapter(show_client.this,addclientList);

        listshow= findViewById(R.id.listshow);


        listshow.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                addclient addclient = addclientList.get(position);
                showUpdateDialog(addclient.getClientId(),addclient.getClientname(),addclient.getAddress(),addclient.getCity(),addclient.getZip(),addclient.getNumber());
                return false;
            }
        });

    }




    @Override
    protected void onStart() {

     databaseReference.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

         for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

             addclient addclient = dataSnapshot1.getValue(addclient.class);
             addclientList.add(addclient);
         }
          listshow.setAdapter(cAdapter);


         }


         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });
        super.onStart();
    }



    private void showUpdateDialog(final String clientid,final String clientname, final String address, final String city, final String zip, final String number){


        AlertDialog.Builder dialogBuilder= new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update,null);
        dialogBuilder.setView(dialogView);

        final EditText editText1=(EditText)dialogView.findViewById(R.id.editText1);
        final EditText editText2=(EditText)dialogView.findViewById(R.id.editText2);
        final EditText editText3=(EditText)dialogView.findViewById(R.id.editText3);
        final EditText editText4=(EditText)dialogView.findViewById(R.id.editText4);
        final EditText editText5=(EditText)dialogView.findViewById(R.id.editText5);
        final Button buttonupdate =(Button)dialogView.findViewById(R.id.buttonupdate);
        final Button buttondelete = (Button)dialogView.findViewById(R.id.buttondelete);

        dialogBuilder.setTitle("Updating Client:" +clientname);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=editText1.getText().toString().trim();
                String add=editText2.getText().toString().trim();
                String cit=editText3.getText().toString().trim();
                String zi=editText4.getText().toString().trim();
                String num=editText5.getText().toString().trim();


                updateclient(clientid,name, add, cit, zi, num);
                alertDialog.dismiss();


            }
        });


        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteclient(clientid);
            }
        });

    }

    private boolean updateclient(String id,String name, String add, String cit, String zi, String num){

        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Add Client").child(id);
        addclient addclient= new addclient(id,name,add,zi,cit,num);
        databaseReference.setValue(addclient);
        Toast.makeText(this,"Updated Data",Toast.LENGTH_LONG).show();
        return true;
    }


    private boolean deleteclient(String clientid){
         DatabaseReference databaseReference = firebaseDatabase.getInstance().getReference("Add Client").child(clientid);
         databaseReference.removeValue();
        Toast.makeText(this,"Deleted Data",Toast.LENGTH_LONG).show();
        return true;
    }





}
