package com.asc.home.Activity.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.asc.home.Activity.Main.Main;
import com.asc.home.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Sign_up_Fragment extends Fragment {
    Button button5;

    public Sign_up_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_sign_up_, container, false);
        button5=root.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Main.class));
            }
        });
       return root;
    }

}
