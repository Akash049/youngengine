package com.asc.home.Activity.Login.IntroductionFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asc.home.Activity.Login.LoginScreen;
import com.asc.home.Activity.Login.IntroductionScreens;
import com.asc.home.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Work extends Fragment {
TextView skip_work,work_next;
    public Work() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_work, container, false);
        initialise(root);
        return root;
    }
    public void initialise(final View root)
    {
        skip_work=root.findViewById(R.id.skip_work);
        work_next=root.findViewById(R.id.work_next);
        skip_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getActivity(), LoginScreen.class));
            }
        });
        work_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((IntroductionScreens)getActivity()).work_change_page();
            }
        });
    }
}
