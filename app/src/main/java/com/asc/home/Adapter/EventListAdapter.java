package com.asc.home.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asc.home.Activity.Mission;
import com.asc.home.Interface.Itemclicklistener;
import com.asc.home.Model.EventModel;
import com.asc.home.R;

import java.util.ArrayList;

public class EventListAdapter extends RecyclerView.Adapter<EventViewHolder>   {
   Context c;
   ArrayList<EventModel> eventModels;

    public EventListAdapter(Context c, ArrayList<EventModel> eventModels) {
        this.c = c;
        this.eventModels = eventModels;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_event,parent,false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {

        holder.mtitle.setText(eventModels.get(position).getTitle());
        holder.mcompanyname.setText(eventModels.get(position).getCompanyname());
//        holder.mstatus.setText(eventModels.get(position).getStatus());
//        holder.mdate.setText(eventModels.get(position).getDate());
        holder.mimg.setImageResource(eventModels.get(position).getImg());
        holder.setItemclicklistener(new Itemclicklistener() {
            @Override
            public void onitemclickListener(View v, int position) {
                Intent intent=new Intent(c, Mission.class);
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }
}
