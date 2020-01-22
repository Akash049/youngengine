package com.asc.home.Activity.Social;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.asc.home.Adapter.MyAdapter;
import com.asc.home.Model.Model;
import com.asc.home.R;

import java.util.ArrayList;

public class Social extends AppCompatActivity {
    RecyclerView mrecyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        mrecyclerView=findViewById(R.id.recyclerView10);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter= new MyAdapter(this,getMyList());
        mrecyclerView.setAdapter(myAdapter);
    }
    private ArrayList<Model> getMyList(){
        ArrayList<Model> models=new ArrayList<>();
        Model m=new Model();
        m.setTitle("Python");
        m.setCompanyname("wiztute");
        m.setStatus("ONGOING");
        m.setDate("15 jan 2020");
        m.setImg(R.drawable.images);
        models.add(m);

        m=new Model();
        m.setTitle("Open a product and buy that product");
        m.setCompanyname("wiztute");
        m.setStatus("ONGOING");
        m.setDate("02 jan 2020");
        m.setImg(R.drawable.images);
        models.add(m);

        m=new Model();
        m.setTitle("Data science");
        m.setCompanyname("wiztute");
        m.setStatus("COMPLETED");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.images);
        models.add(m);

        m=new Model();
        m.setTitle("Blockchain");
        m.setCompanyname("Wiztute");
        m.setStatus("COMPLETED");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.images);
        models.add(m);

        m=new Model();
        m.setTitle("Data science");
        m.setCompanyname("wiztute");
        m.setStatus("COMPLETED");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.images);
        models.add(m);

        m=new Model();
        m.setTitle("Data science");
        m.setCompanyname("wiztute");
        m.setStatus("COMPLETED");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.images);
        models.add(m);

        m=new Model();
        m.setTitle("Data science");
        m.setCompanyname("wiztute");
        m.setStatus("COMPLETED");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.images);
        models.add(m);
        return models;
    }

}
