package com.asc.home.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.asc.home.Model.NavDataModel;
import com.asc.home.R;

public class DrawerListItemAdapter extends ArrayAdapter<NavDataModel> {

    Context mContext;
    int layoutResourceId;
    NavDataModel data[] = null;

    public DrawerListItemAdapter(@NonNull Context context, int resource, NavDataModel[] data) {
        super(context, resource, data);
        this.layoutResourceId = resource;
        this.mContext = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
        TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);

        TextView userName = (TextView) listItem.findViewById(R.id.name);
        TextView userLevel = (TextView) listItem.findViewById(R.id.level);
        LinearLayout navHeader = (LinearLayout) listItem.findViewById(R.id.nav_head);
        LinearLayout navItem = (LinearLayout) listItem.findViewById(R.id.nav_val);

        NavDataModel folder = data[position];

        if( folder.getUserName() != null ){
            navHeader.setVisibility(View.VISIBLE);
            navItem.setVisibility(View.GONE);
            userName.setText(folder.getUserName());
            //userLevel.setText(folder.getLevelName());
        }else{
            navHeader.setVisibility(View.GONE);
            navItem.setVisibility(View.VISIBLE);
            imageViewIcon.setImageResource(folder.icon);
            textViewName.setText(folder.name);
        }

        return listItem;
    }
}
