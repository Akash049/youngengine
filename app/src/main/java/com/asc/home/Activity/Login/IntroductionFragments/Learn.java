package com.asc.home.Activity.Login.IntroductionFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.asc.home.Activity.Login.IntroductionScreens;

import com.asc.home.Activity.Login.LoginScreen;
import com.asc.home.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Learn extends Fragment {
TextView skip_learn,next_learn;

    public Learn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_learn, container, false);
        initialize(root);
        return root;
    }

    public void initialize(final View root)
    {
        skip_learn=root.findViewById(R.id.skip_learn);
        next_learn=root.findViewById(R.id.next_learn);
        skip_learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginScreen.class));
            }
        });
        next_learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((IntroductionScreens)getActivity()).learn_change_page();
            }
        });
    }
}
