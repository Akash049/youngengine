package com.asc.home.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asc.home.Activity.Mission.Mission;
import com.asc.home.Interface.Itemclicklistener;
import com.asc.home.Model.EventModel;
import com.asc.home.Model.NewModel;
import com.asc.home.R;

import java.util.ArrayList;

public class NewListAdapter extends RecyclerView.Adapter<NewViewHolder> {

    Context c;
    ArrayList<NewModel> newModels;

    public NewListAdapter(Context c, ArrayList<NewModel> newModels) {
        this.c = c;
        this.newModels = newModels;
    }

    @NonNull
    @Override
    public NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.youngengine_list_item,parent,false);
        return new NewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewViewHolder holder, int position) {

        holder.event_title.setText(newModels.get(position).getNew_title());
        holder.event_image.setImageResource(newModels.get(position).getNew_image());
        holder.event_tasks.setText(newModels.get(position).getTasks());
        holder.event_taskcoins.setText(newModels.get(position).getTaskcoins());
        holder.event_date.setText(newModels.get(position).getNew_date());
        holder.setItemclicklistener(new Itemclicklistener() {
        public void onitemclickListener(View v, int position) {
            Intent intent=new Intent(c, Mission.class);
            c.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return newModels.size();
    }
    public void filterlist(ArrayList<NewModel> filteredlist)
    {
        newModels = filteredlist;
        notifyDataSetChanged();
    }
}
