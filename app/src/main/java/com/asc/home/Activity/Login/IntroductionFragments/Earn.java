package com.asc.home.Activity.Login.IntroductionFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asc.home.Activity.Login.LoginScreen;
import com.asc.home.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Earn extends Fragment {
   TextView start;
    public Earn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_earn, container, false);

        start=root.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginScreen.class));
            }
        });
    return root;
    }
}
