package com.example.casediary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.view.LayoutInflater.*;

class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    ArrayList<Deal> list;
    public AdapterClass(ArrayList<Deal>list){

        this.list=list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = from(parent.getContext()).inflate(R.layout.card_holder, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.clientname.setText(list.get(position).getClientname());
        holder.address.setText(list.get(position).getAddress());
        holder.city.setText(list.get(position).getCity());
        holder.zip.setText(list.get(position).getZip());
        holder.number.setText(list.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder {


        TextView clientname,address,city,zip,number;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            clientname = itemView.findViewById(R.id.textName);
            address = itemView.findViewById(R.id.textAd);
            city= itemView.findViewById(R.id.textCity);
            zip = itemView.findViewById(R.id.textZip);
            number=itemView.findViewById(R.id.textNum);
        }
    }
}

