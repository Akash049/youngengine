package com.asc.home.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asc.home.Interface.Itemclicklistener;
import com.asc.home.R;

public class LearningsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView learnings_image;
    TextView learnings_title,skill1,skill2,skill3;
    Itemclicklistener itemclicklistener;

    public LearningsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.learnings_image=itemView.findViewById(R.id.learnings_image);
        this.learnings_title=itemView.findViewById(R.id.learnings_title);
        this.skill1=itemView.findViewById(R.id.skill1);
        this.skill2=itemView.findViewById(R.id.skill2);
        this.skill3=itemView.findViewById(R.id.skill3);
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
