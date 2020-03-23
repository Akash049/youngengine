package com.asc.home.Activity.Fragment;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.asc.home.Activity.ViewModels.DashboardViewModel;
import com.asc.home.Adapter.NewListAdapter;
import com.asc.home.Adapter.SliderImageAdapter;
import com.asc.home.Model.EventModel;
import com.asc.home.Adapter.EventListAdapter;
import com.asc.home.Model.NewModel;
import com.asc.home.R;

import java.util.ArrayList;

public class Active extends Fragment  {
    private DashboardViewModel dashboardViewModel;
    RecyclerView mirecyclerView;
    NewListAdapter dAdapter;
    TextView ongoingTextView;
    TextView completedTextView;
    LinearLayout ongoingred,completedred;
    private SliderImageAdapter sliderImageAdapter;
    private ViewPager imageSlider;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //Initialization at one place
        ongoingTextView = root.findViewById(R.id.textView3);
        completedTextView = root.findViewById(R.id.textView7);
        ongoingred=root.findViewById(R.id.ongoingred);
        completedred=root.findViewById(R.id.completedred);

        // On the start of app, by default ongoing has to be selected
        ongoingTextView.setTextColor(getResources().getColor(R.color.red));
        completedTextView.setTextColor(getResources().getColor(R.color.unselected_grey));
        ongoingred.setVisibility(View.VISIBLE);

        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onChanged(@Nullable String s) {
                // textView.setText(s);
              //  imageSlider = (ViewPager) getView().findViewById(R.id.image_slider);
                //sliderImageAdapter = new SliderImageAdapter(getContext());
                //imageSlider.setAdapter(sliderImageAdapter);
                mirecyclerView = getView().findViewById(R.id.recyclerview5);
                mirecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                dAdapter= new NewListAdapter(getContext(),getMyList());
                mirecyclerView.setAdapter(dAdapter);


              ongoingTextView.setOnClickListener(new View.OnClickListener() {
                  @RequiresApi(api = Build.VERSION_CODES.M)
                  @Override
                  public void onClick(View v) {
                      mirecyclerView = getView().findViewById(R.id.recyclerview5);
                      mirecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                      dAdapter= new NewListAdapter(getContext(),getMyList());
                      mirecyclerView.setAdapter(dAdapter);

                      //On clicking ongoing make it blue
                      ongoingTextView.setTextColor(getResources().getColor(R.color.red));
                      completedTextView.setTextColor(getResources().getColor(R.color.unselected_grey));
                      completedred.setVisibility(View.GONE);
                      ongoingred.setVisibility(View.VISIBLE);

                  }

              });

             completedTextView.setOnClickListener(new View.OnClickListener() {
                 @RequiresApi(api = Build.VERSION_CODES.M)
                 @Override
                 public void onClick(View v) {
                     mirecyclerView = getView().findViewById(R.id.recyclerview5);
                     mirecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                     dAdapter= new NewListAdapter(getContext(),MyList());
                     mirecyclerView.setAdapter(dAdapter);

                     //On clicking ongoing make it blue
                     completedTextView.setTextColor(getResources().getColor(R.color.red));
                     ongoingTextView.setTextColor(getResources().getColor(R.color.unselected_grey));
                     completedred.setVisibility(View.VISIBLE);
                     ongoingred.setVisibility(View.GONE);

                 }
             });
            }
        });
        return root;
    }
    private ArrayList<NewModel> getMyList() {
        ArrayList<NewModel> newModels =new ArrayList<>();
        NewModel m=new NewModel();
        m.setNew_title("MUN In IITD");
        m.setNew_date("21st Feb, 2020");
        m.setTaskcoins("133");
        m.setTasks("5");
        m.setNew_image(R.drawable.people);
        newModels.add(m);

        m=new NewModel();
        m.setNew_title("College Fest In Gargi");
        m.setNew_date("21st Feb, 2020");
        m.setTaskcoins("133");
        m.setTasks("5");
        m.setNew_image(R.drawable.people2);
        newModels.add(m);


        return newModels;
    }


    private ArrayList<NewModel> MyList() {
        ArrayList<NewModel> newModels =new ArrayList<>();
        NewModel m=new NewModel();
        m.setNew_title("Cultural Fest");
        m.setNew_date("21st Feb, 2020");
        m.setTaskcoins("133");
        m.setTasks("5");
        m.setNew_image(R.drawable.people3);
        newModels.add(m);
        return newModels;

    }
}