package com.asc.home.Activity.Other;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.asc.home.Adapter.EventListAdapter2;
import com.asc.home.Model.EventModel;
import com.asc.home.R;

import java.util.ArrayList;

public class Wallet extends AppCompatActivity {
    RecyclerView mrecyclerView;
    EventListAdapter2 eventListAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Wallet");

        mrecyclerView=findViewById(R.id.myntra);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventListAdapter2 = new EventListAdapter2(this,getMyList());
        mrecyclerView.setAdapter(eventListAdapter2);
    }


    private ArrayList<EventModel> getMyList(){
        ArrayList<EventModel> eventModels =new ArrayList<>();
        EventModel m=new EventModel();
        m.setTitle("Python");
        m.setCompanyname("wiztute");
        m.setStatus("ONGOING");
        m.setDate("15 jan 2020");
        m.setImg(R.drawable.images);
        eventModels.add(m);

        m=new EventModel();
        m.setTitle("Open a product and buy that product");
        m.setCompanyname("wiztute");
        m.setStatus("ONGOING");
        m.setDate("02 jan 2020");
        m.setImg(R.drawable.images);
        eventModels.add(m);

        m=new EventModel();
        m.setTitle("Data science");
        m.setCompanyname("wiztute");
        m.setStatus("COMPLETED");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.images);
        eventModels.add(m);

        m=new EventModel();
        m.setTitle("Blockchain");
        m.setCompanyname("Wiztute");
        m.setStatus("COMPLETED");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.images);
        eventModels.add(m);

        m=new EventModel();
        m.setTitle("Data science");
        m.setCompanyname("wiztute");
        m.setStatus("COMPLETED");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.images);
        eventModels.add(m);

        m=new EventModel();
        m.setTitle("Data science");
        m.setCompanyname("wiztute");
        m.setStatus("COMPLETED");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.images);
        eventModels.add(m);

        m=new EventModel();
        m.setTitle("Data science");
        m.setCompanyname("wiztute");
        m.setStatus("COMPLETED");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.images);
        eventModels.add(m);
        return eventModels;
    }
}
