package com.asc.home.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asc.home.Itemclicklistener;
import com.asc.home.R;

public class    Myholder extends RecyclerView.ViewHolder implements View.OnClickListener{
ImageView mimg;
TextView mtitle,mcompanyname,mstatus,mdate;
Itemclicklistener itemclicklistener;

    Myholder(@NonNull View itemView) {
        super(itemView);
        this.mimg = itemView.findViewById(R.id.img);
        this.mtitle = itemView.findViewById(R.id.titler);
        this.mcompanyname = itemView.findViewById(R.id.companyname);
        this.mstatus = itemView.findViewById(R.id.status);
        this.mdate = itemView.findViewById(R.id.Coins);
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

//    public void onClick() {
  //      Intent intent=new Intent(,Mission.class);
    //    startActivity(intent);
    //}//
}
