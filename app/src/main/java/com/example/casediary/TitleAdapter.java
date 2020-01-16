package com.example.casediary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TitleAdapter  extends RecyclerView.Adapter<TitleAdapter.MyViewHolder> {


    private Context mContext;
    private ArrayList<String> titleList;
    private CustomitemClickListener clickListener;

    public TitleAdapter(Context mContext, ArrayList<String> titleList, CustomitemClickListener clickListener) {
        this.mContext = mContext;
        this.titleList = titleList;
        this.clickListener = clickListener;
    }

    public TitleAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(mContext).inflate(R.layout.title_layout,parent,  false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemCLick(view,viewHolder.getPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.titleText.setText(titleList.get(position).replace("_", "  "));
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titleText;
        public MyViewHolder(View itemView){
            super(itemView);
            titleText = (TextView)itemView.findViewById(R.id.title_text);
        }
    }

    }

