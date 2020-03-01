package com.asc.home.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asc.home.Interface.Itemclicklistener;
import com.asc.home.R;

public class NewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView event_image;
    TextView event_title,event_date,event_tasks,event_taskcoins;
    Itemclicklistener itemclicklistener;

    public NewViewHolder(@NonNull View itemView) {
        super(itemView);
        this.event_image=itemView.findViewById(R.id.new_image);
        this.event_title=itemView.findViewById(R.id.new_title);
        this.event_date=itemView.findViewById(R.id.new_date);
        this.event_tasks=itemView.findViewById(R.id.tasks);
        this.event_taskcoins=itemView.findViewById(R.id.taskcoins);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemclicklistener.onitemclickListener(v,getLayoutPosition());
    }
    public void setItemclicklistener(Itemclicklistener ic)
    {
        this.itemclicklistener=ic;
    }
}
