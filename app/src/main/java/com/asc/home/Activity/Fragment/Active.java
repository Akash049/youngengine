package com.asc.home.Activity.Fragment;

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
import androidx.viewpager.widget.ViewPager;

import com.asc.home.Activity.ViewModels.DashboardViewModel;
import com.asc.home.Adapter.SliderImageAdapter;
import com.asc.home.Model.EventModel;
import com.asc.home.Adapter.EventListAdapter;
import com.asc.home.R;

import java.util.ArrayList;

public class Active extends Fragment {

    private DashboardViewModel dashboardViewModel;
    RecyclerView mirecyclerView;
    EventListAdapter dAdapter;
    TextView ongoingTextView;
    TextView completedTextView;
    private SliderImageAdapter sliderImageAdapter;
    private ViewPager imageSlider;
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
                imageSlider = (ViewPager) getView().findViewById(R.id.image_slider);
                sliderImageAdapter = new SliderImageAdapter(getContext());
                imageSlider.setAdapter(sliderImageAdapter);
                mirecyclerView = getView().findViewById(R.id.recyclerview5);
                mirecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                dAdapter= new EventListAdapter(getContext(),getMyList());
                mirecyclerView.setAdapter(dAdapter);


              ongoingTextView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      mirecyclerView = getView().findViewById(R.id.recyclerview5);
                      mirecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                      dAdapter= new EventListAdapter(getContext(),getMyList());
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

                     dAdapter= new EventListAdapter(getContext(),MyList());
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
    private ArrayList<EventModel> getMyList() {
        ArrayList<EventModel> eventModels = new ArrayList<>();
        EventModel m = new EventModel();
        m.setTitle("Python");
        m.setCompanyname("wiztute");
        m.setStatus("ongoing");
        m.setDate("15 jan 2020");
        m.setImg(R.drawable.images);
        eventModels.add(m);

        m = new EventModel();
        m.setTitle("Android");
        m.setCompanyname("wiztute");
        m.setStatus("ongoing");
        m.setDate("02 jan 2020");
        m.setImg(R.drawable.images);
        eventModels.add(m);

       /* m = new EventModel();
        m.setTitle("data science");
        m.setCompanyname("wiztute");
        m.setStatus("completed");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.logo);
        eventModels.add(m);

        */
        return eventModels;
    }


    private ArrayList<EventModel> MyList() {
        ArrayList<EventModel> eventModels = new ArrayList<>();
        EventModel m = new EventModel();
        m.setTitle("Data science");
        m.setCompanyname("wiztute");
        m.setStatus("completed");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.images);
        eventModels.add(m);
        return eventModels;

    }
}