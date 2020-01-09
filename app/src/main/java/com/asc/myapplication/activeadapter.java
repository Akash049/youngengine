package com.asc.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class activeadapter extends RecyclerView.Adapter<activeadapter.activeviewholder> {
    private String[] data;
    public activeadapter(String[] data)
{

 this.data=data;
}
    @NonNull
    @Override
    public activeviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.list_item_layout,parent,false);
    return new activeviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull activeviewholder holder, int position) {
String title=data [position];
holder.title.setText(title);
}
    @Override
    public int getItemCount() {
        return data.length;
    }

    public class activeviewholder extends RecyclerView.ViewHolder{
        TextView title;

        public activeviewholder(@NonNull View itemView) {
            super(itemView);
            title =(TextView) itemView.findViewById(R.id.title);

        }
    }
}
