package com.asc.home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asc.home.Model.Model;
import com.asc.home.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<Myholder>   {
   Context c;
   ArrayList<Model> models;

    public MyAdapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_event,null);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {

        holder.mtitle.setText(models.get(position).getTitle());
        holder.mcompanyname.setText(models.get(position).getCompanyname());
        holder.mstatus.setText(models.get(position).getStatus());
        holder.mdate.setText(models.get(position).getDate());
        holder.mimg.setImageResource(models.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
