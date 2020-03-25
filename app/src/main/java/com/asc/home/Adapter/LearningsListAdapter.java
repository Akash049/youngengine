package com.asc.home.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asc.home.Activity.Mission.Mission;
import com.asc.home.Holders.LearningsViewHolder;
import com.asc.home.Interface.Itemclicklistener;
import com.asc.home.Model.LearningsModel;
import com.asc.home.R;

import java.util.ArrayList;

public class LearningsListAdapter extends RecyclerView.Adapter<LearningsViewHolder> {

    Context c;
    ArrayList<LearningsModel> learningsModels;

    public LearningsListAdapter(Context c, ArrayList<LearningsModel> learningsModels) {
        this.c = c;
        this.learningsModels = learningsModels;
    }

    @NonNull
    @Override
    public LearningsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.learnings_list_item,parent,false);
         return new LearningsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LearningsViewHolder holder, int position) {

        holder.learnings_image.setImageResource(learningsModels.get(position).getLearnings_image());
        holder.learnings_title.setText(learningsModels.get(position).getLearnings_title());
        holder.skill1.setText(learningsModels.get(position).getSkill1());
        holder.skill2.setText(learningsModels.get(position).getSkill2());
        holder.skill3.setText(learningsModels.get(position).getSkill3());
        holder.setItemclicklistener(new Itemclicklistener() {
            public void onitemclickListener(View v, int position) {
                Intent intent=new Intent(c, Mission.class);
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return learningsModels.size();
    }
}
