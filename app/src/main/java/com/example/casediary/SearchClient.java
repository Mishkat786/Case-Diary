package com.example.casediary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchClient extends AppCompatActivity {

    DatabaseReference ref;
    ArrayList<Deal> list;
    RecyclerView recyclerView;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_client);

        ref= FirebaseDatabase.getInstance().getReference("Add Client");

        recyclerView = findViewById(R.id.rv);
        searchView = findViewById(R.id.searchView);



    }

    @Override
    protected void onStart() {
        super.onStart();

        if (ref!=null){

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()){
                        list = new ArrayList<>();

                        for (DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            list.add(ds.getValue(Deal.class));

                        }
                        AdapterClass adapterClass = new AdapterClass(list);

                        recyclerView.setAdapter(adapterClass);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(SearchClient.this,"Failed to load data",Toast.LENGTH_LONG).show();

                }
            });
        }

        if(searchView!=null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
    }


    private  void search (String str){
        ArrayList<Deal> mylist = new ArrayList<>();
        for(Deal object : list){

            if (object.getClientname().toLowerCase().contains(str.toLowerCase()));{
                mylist.add(object);
            }
        }
        AdapterClass adapterClass = new AdapterClass(mylist);
        recyclerView.setAdapter(adapterClass);
    }

}
