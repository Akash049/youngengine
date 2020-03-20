package com.asc.home.Activity.FAQ;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.asc.home.R;

import java.util.HashMap;
import java.util.List;

public class FAQAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> listgroup;
    HashMap<String,List<String>> listitem;

    public FAQAdapter(Context context, List<String> listgroup, HashMap<String, List<String>> listitem) {
        this.context = context;
        this.listgroup = listgroup;
        this.listitem = listitem;
    }

    @Override
    public int getGroupCount() {

        return listgroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listitem.get(this.listgroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listgroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listitem.get(this.listgroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String group=(String) getGroup(groupPosition);
        if (convertView==null)
        {
            LayoutInflater layoutInflater =(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView=layoutInflater.inflate(R.layout.list_group,null);
        }
        TextView textView=convertView.findViewById(R.id.list_parent);
        textView.setText(group);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String child=(String) getChild(groupPosition,childPosition);
        if (convertView==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.list_item,null);
        }
        TextView textView=convertView.findViewById(R.id.list_child);
        textView.setText(child);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
