package com.example.casediary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class showcase extends AppCompatActivity {

    DatabaseReference databaseReference;
    private List<AddCase>addCaseList;
    private  ListView listView;
    private caseAdapter caseadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcase);

        databaseReference= FirebaseDatabase.getInstance().getReference("Case");
          addCaseList = new ArrayList<>();
          caseadapter = new caseAdapter(showcase.this,addCaseList);
                listView=(ListView)findViewById(R.id.listshow1);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AddCase addCase = addCaseList.get(position);

                        ShowUpdateDialog(addCase.getCaseId(),addCase.getClientname(),addCase.getCasenumber(),addCase.getCasetitle(),addCase.getCourtno(),addCase.getCased(),addCase.getDate());
                    }
                });
    }

    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                addCaseList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    AddCase addCase = dataSnapshot1.getValue(AddCase.class);
                    addCaseList.add(addCase);
                }

                listView.setAdapter(caseadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }

    private void ShowUpdateDialog(final String caseId, String clientname,String casenumber, String casetitle, String courtno, String cased, String date){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_case,null);
        dialogBuilder.setView(dialogView);

       final  EditText editclientname = (EditText)dialogView.findViewById(R.id.editclientname);
        final EditText editcasenumber = (EditText)dialogView.findViewById(R.id.editcasenumber);
        final EditText editcasetitle = (EditText)dialogView.findViewById(R.id.editcasetitle);
        final EditText editcourtno= (EditText)dialogView.findViewById(R.id.editcourtno);
        final EditText editcased = (EditText)dialogView.findViewById(R.id.editcased);
        final EditText editdate = (EditText)dialogView.findViewById(R.id.editdate);

        final Button update = (Button)dialogView.findViewById(R.id.update);
        final Button delete = (Button)dialogView.findViewById(R.id.delete);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editclientname.getText().toString().trim();
                String number = editcasenumber.getText().toString().trim();
                String title = editcasetitle.getText().toString().trim();
                String no = editcourtno.getText().toString().trim();
                String d = editcased.getText().toString().trim();
                String datee = editdate.getText().toString().trim();

                if (TextUtils.isEmpty(number)){
                    editcasenumber.setError("Enter Number");
                    return;
                }
                if (TextUtils.isEmpty(name)){
                    editcasenumber.setError("Enter Name");
                    return;
                }
                if (TextUtils.isEmpty(title)){
                    editcasenumber.setError("Enter title");
                    return;
                }
                if (TextUtils.isEmpty(no)){
                    editcasenumber.setError("Enter Court No");
                    return;
                }
                if (TextUtils.isEmpty(d)){
                    editcasenumber.setError("Enter Date");
                    return;
                }

                updateCase(caseId,name,number,title,no,d,datee);

                alertDialog.dismiss();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletecase(caseId);
            }
        });

    }

    private boolean deletecase(String caseId){

        DatabaseReference dcase = FirebaseDatabase.getInstance().getReference("Case").child(caseId);

        dcase.removeValue();
        Toast.makeText(this,"deleted",Toast.LENGTH_LONG).show();

        return true;
    }

    private boolean updateCase(String Id,String name, String number,String title,String no,String d,String datee){


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Case").child(Id);
        AddCase addCase = new AddCase(Id,name,number,title,no,d,datee);
        databaseReference.setValue(addCase);
        Toast.makeText(this,"Updated Data",Toast.LENGTH_LONG).show();
        return true;
    }


}
