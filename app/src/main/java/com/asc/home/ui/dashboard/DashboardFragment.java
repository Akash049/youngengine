package com.asc.home.ui.dashboard;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asc.home.Model;
import com.asc.home.MyAdapter;
import com.asc.home.R;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
   RecyclerView mirecyclerView;
   MyAdapter dAdapter;
TextView textView3;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //final TextView textView = root.findViewById(R.id.Active);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                // textView.setText(s);
                mirecyclerView = getView().findViewById(R.id.recyclerview5);
                mirecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                dAdapter= new MyAdapter(getContext(),getMyList());
                mirecyclerView.setAdapter(dAdapter);
              textView3=getView().findViewById(R.id.textView3);
              textView3.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      



                  }
              });


            }
        });
        return root;
    }
    private ArrayList<Model> getMyList() {
        ArrayList<Model> models = new ArrayList<>();
        Model m = new Model();
        m.setTitle("python");
        m.setCompanyname("wiztute");
        m.setStatus("ongoing");
        m.setDate("15 jan 2020");
        m.setImg(R.drawable.logo);
        models.add(m);

        m = new Model();
        m.setTitle("android");
        m.setCompanyname("wiztute");
        m.setStatus("ongoing");
        m.setDate("02 jan 2020");
        m.setImg(R.drawable.logo);
        models.add(m);

        m = new Model();
        m.setTitle("data science");
        m.setCompanyname("wiztute");
        m.setStatus("completed");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.logo);
        models.add(m);
        return models;
    }
}