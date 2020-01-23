package com.asc.home.Activity.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.asc.home.Activity.ViewModels.HomeViewModel;
import com.asc.home.Adapter.SliderImageAdapter;
import com.asc.home.Model.EventModel;
import com.asc.home.Adapter.EventListAdapter;
import com.asc.home.R;

import java.util.ArrayList;

public class Home extends Fragment{

    // Container Vars
    private HomeViewModel homeViewModel;
    private EventListAdapter eventListAdapter;
    private SliderImageAdapter sliderImageAdapter;

    // Wigets
    private RecyclerView mrecyclerView;
    private ViewPager imageSlider;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel=ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initialize(root);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    private void initialize(View root){
        imageSlider = (ViewPager) root.findViewById(R.id.image_slider);
        sliderImageAdapter = new SliderImageAdapter(getContext());
        imageSlider.setAdapter(sliderImageAdapter);
        mrecyclerView=root.findViewById(R.id.recyclerView3);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        eventListAdapter = new EventListAdapter(getContext(),getMyList());
        mrecyclerView.setAdapter(eventListAdapter);
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
