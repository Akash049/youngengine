package com.asc.home.Activity.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asc.home.Activity.AtclickActivity;
import com.asc.home.Model.Model;
import com.asc.home.Adapter.MyAdapter;
import com.asc.home.R;

import java.util.ArrayList;

//import technolifestyle.com.imageslider.FlipperLayout;
//import technolifestyle.com.imageslider.FlipperView;

public class Home_Fragment_Activity extends Fragment {
    private HomeViewModel homeViewModel;
    //VideoView videoview;
    RecyclerView mrecyclerView;
    MyAdapter myAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel=ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
                mrecyclerView = getView().findViewById(R.id.recyclerView3);
                mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                myAdapter= new MyAdapter(getContext(),getMyList());
                mrecyclerView.setAdapter(myAdapter);

                 root.findViewById(R.id.textView2) .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), AtclickActivity.class));
                    }
                });

            }
        });

//        FlipperLayout flipperLayout = (FlipperLayout) root.findViewById(R.id.flipper_layout);
//        int num_of_pages = 3;
//        for (int i = 0; i < num_of_pages; i++) {
//            FlipperView view = new FlipperView(getContext());
//            view.setImageScaleType(ImageView.ScaleType.CENTER_CROP) // You can use any ScaleType
//                    .setDescription("Description") // Add custom description for your image in the flipper view
//                    .setImage(R.mipmap.ic_launcher, new Function2<ImageView, Object, Unit>() {
//                        @Override
//                        public Unit invoke(ImageView imageView, Object image) {
//                            // As per the user discretion as to how they want to load the URL
//                            /* E.g since an image of Drawable type is sent as a param in setImage method, The Object
//                             * image will be of type Drawable
//                             * imageView.setImageDrawable((Drawable)image);
//                             */
//                            return Unit.INSTANCE;
//                        }
//                    })
//                    .setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
//                        @Override
//                        public void onFlipperClick(FlipperView flipperView) {
//                            // Handle View Click here
//                        }
//                    });
//            flipperLayout.setScrollTimeInSec(5); //setting up scroll time, by default it's 3 seconds
//            flipperLayout.getScrollTimeInSec(); //returns the scroll time in sec
//            flipperLayout.getCurrentPagePosition(); //returns the current position of pager
//            flipperLayout.addFlipperView(view);
//        }

        return root;
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
