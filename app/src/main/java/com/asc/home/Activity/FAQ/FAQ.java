package com.asc.home.Activity.FAQ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.asc.home.Activity.Main.Main;
import com.asc.home.Activity.Wallet.Wallet;
import com.asc.home.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FAQ extends AppCompatActivity  {
   ExpandableListView expandableListView;
   List<String> listgroup;
   HashMap<String,List<String>> listitem;
   FAQAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        expandableListView=findViewById(R.id.exapandable_view);
        listgroup=new ArrayList<>();
        listitem=new HashMap<>();
        adapter= new FAQAdapter(this,listgroup,listitem);
        expandableListView.setAdapter(adapter);
        initlistdata();


    }
    private void initlistdata()
    {
        listgroup.add(getString(R.string.faq1));
        listgroup.add(getString(R.string.faq2));
        listgroup.add(getString(R.string.faq3));
        listgroup.add(getString(R.string.faq4));
        listgroup.add(getString(R.string.faq5));

        String[] array;
        List<String> list1 = new ArrayList<>();
        array=getResources().getStringArray(R.array.faq1);
        for (String item : array)
        {
            list1.add(item);
        }
        List<String> list2 = new ArrayList<>();
        array=getResources().getStringArray(R.array.faq2);
        for (String item : array)
        {
            list2.add(item);
        }
        List<String> list3 = new ArrayList<>();
        array=getResources().getStringArray(R.array.faq3);
        for (String item : array)
        {
            list3.add(item);
        }
        List<String> list4 = new ArrayList<>();
        array=getResources().getStringArray(R.array.faq4);
        for (String item : array)
        {
            list4.add(item);
        }
        List<String> list5 = new ArrayList<>();
        array=getResources().getStringArray(R.array.faq5);
        for (String item : array)
        {
            list5.add(item);
        }

        listitem.put(listgroup.get(0),list1);
        listitem.put(listgroup.get(1),list2);
        listitem.put(listgroup.get(2),list3);
        listitem.put(listgroup.get(3),list4);
        listitem.put(listgroup.get(4),list5);
        adapter.notifyDataSetChanged();

    }

}
