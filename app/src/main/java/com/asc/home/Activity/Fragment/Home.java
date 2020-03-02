package com.asc.home.Activity.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.asc.home.Activity.ViewModels.HomeViewModel;
import com.asc.home.Adapter.NewListAdapter;
import com.asc.home.Adapter.SliderImageAdapter;
import com.asc.home.ExampleBottomSheetDialog;
import com.asc.home.Model.EventModel;
import com.asc.home.Adapter.EventListAdapter;
import com.asc.home.Model.NewModel;
import com.asc.home.R;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class Home extends Fragment {

    // Container Vars
    private HomeViewModel homeViewModel;
    private NewListAdapter newListAdapter;
    private SliderImageAdapter sliderImageAdapter;
    LinearLayout sort;
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

    private void initialize(final View root){
      //  imageSlider = (ViewPager) root.findViewById(R.id.image_slider);
     //   sliderImageAdapter = new SliderImageAdapter(getContext());
     //   imageSlider.setAdapter(sliderImageAdapter);
        sort=root.findViewById(R.id.sort);
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExampleBottomSheetDialog bottomSheet = new ExampleBottomSheetDialog();
                bottomSheet.show(getFragmentManager(),"exampleBottomSheet");
            }
        });
        mrecyclerView=root.findViewById(R.id.new_view);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newListAdapter = new NewListAdapter(getContext(),getMyList());
        mrecyclerView.setAdapter(newListAdapter);
    }

    private ArrayList<NewModel> getMyList(){
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

        m=new NewModel();
        m.setNew_title("Cultural Fest");
        m.setNew_date("21st Feb, 2020");
        m.setTaskcoins("133");
        m.setTasks("5");
        m.setNew_image(R.drawable.people3);
        newModels.add(m);


        return newModels;
    }

}
