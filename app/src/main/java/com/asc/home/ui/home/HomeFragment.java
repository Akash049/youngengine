package com.asc.home.ui.home;

import android.R.layout;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.MediaController;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asc.home.Model;
import com.asc.home.MyAdapter;
import com.asc.home.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    //VideoView videoview;
    RecyclerView mrecyclerView;
    MyAdapter myAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
                homeViewModel=ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
                mrecyclerView = getView().findViewById(R.id.recyclerView3);
                mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                myAdapter= new MyAdapter(getContext(),getMyList());
                mrecyclerView.setAdapter(myAdapter);



            }
        });

        return root;
    }
    private ArrayList<Model> getMyList(){
        ArrayList<Model> models=new ArrayList<>();
        Model m=new Model();
        m.setTitle("python");
        m.setCompanyname("wiztute");
        m.setStatus("ongoing");
        m.setDate("15 jan 2020");
        m.setImg(R.drawable.logo);
        models.add(m);

        m=new Model();
        m.setTitle("android");
        m.setCompanyname("wiztute");
        m.setStatus("ongoing");
        m.setDate("02 jan 2020");
        m.setImg(R.drawable.logo);
        models.add(m);

        m=new Model();
        m.setTitle("data science");
        m.setCompanyname("wiztute");
        m.setStatus("completed");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.logo);
        models.add(m);
      return models;
    }

}
