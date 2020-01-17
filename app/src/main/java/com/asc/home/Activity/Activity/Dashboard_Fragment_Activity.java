package com.asc.home.Activity.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asc.home.Model.Model;
import com.asc.home.Adapter.MyAdapter;
import com.asc.home.R;

import java.util.ArrayList;

public class Dashboard_Fragment_Activity extends Fragment {

    private DashboardViewModel dashboardViewModel;
    RecyclerView mirecyclerView;
    MyAdapter dAdapter;
    TextView ongoingTextView;
    TextView completedTextView;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //Initialization at one place
        ongoingTextView = root.findViewById(R.id.textView3);
        completedTextView = root.findViewById(R.id.textView7);

        // On the start of app, by default ongoing has to be selected
        ongoingTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
        completedTextView.setTextColor(getResources().getColor(R.color.unselected_grey));

        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                // textView.setText(s);
                mirecyclerView = getView().findViewById(R.id.recyclerview5);
                mirecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                dAdapter= new MyAdapter(getContext(),getMyList());
                mirecyclerView.setAdapter(dAdapter);


              ongoingTextView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      mirecyclerView = getView().findViewById(R.id.recyclerview5);
                      mirecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                      dAdapter= new MyAdapter(getContext(),getMyList());
                      mirecyclerView.setAdapter(dAdapter);

                      //On clicking ongoing make it blue
                      ongoingTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
                      completedTextView.setTextColor(getResources().getColor(R.color.unselected_grey));
                  }

              });

             completedTextView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     mirecyclerView = getView().findViewById(R.id.recyclerview5);
                     mirecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                     dAdapter= new MyAdapter(getContext(),MyList());
                     mirecyclerView.setAdapter(dAdapter);

                     //On clicking ongoing make it blue
                     completedTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
                     ongoingTextView.setTextColor(getResources().getColor(R.color.unselected_grey));

                 }
             });




            }
        });
        return root;
    }
    private ArrayList<Model> getMyList() {
        ArrayList<Model> models = new ArrayList<>();
        Model m = new Model();
        m.setTitle("Python");
        m.setCompanyname("wiztute");
        m.setStatus("ongoing");
        m.setDate("15 jan 2020");
        m.setImg(R.drawable.images);
        models.add(m);

        m = new Model();
        m.setTitle("Android");
        m.setCompanyname("wiztute");
        m.setStatus("ongoing");
        m.setDate("02 jan 2020");
        m.setImg(R.drawable.images);
        models.add(m);

       /* m = new Model();
        m.setTitle("data science");
        m.setCompanyname("wiztute");
        m.setStatus("completed");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.logo);
        models.add(m);

        */
        return models;
    }


    private ArrayList<Model> MyList() {
        ArrayList<Model> models = new ArrayList<>();
        Model m = new Model();
        m.setTitle("Data science");
        m.setCompanyname("wiztute");
        m.setStatus("completed");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.images);
        models.add(m);
        return models;

    }
}