package com.asc.home.Activity.Home.DashboardFragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asc.home.Activity.Learnings.Learning;
import com.asc.home.Activity.Home.Dashboard;
import com.asc.home.Activity.Other.Redeem;
import com.asc.home.Activity.Other.Referral;
import com.asc.home.Activity.Other.Settings;
import com.asc.home.Activity.ViewModels.ProfileviewModel;
import com.asc.home.R;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends Fragment {

    LinearLayout rp,set,rc,learn;
    ImageView open;
    TextView name;
    private FirebaseAuth auth;

    private ProfileviewModel profileviewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileviewModel =
                ViewModelProviders.of(this).get(ProfileviewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        initialize(root);

        //Apply the user name
        String email = auth.getCurrentUser().getEmail();
        name.setText(email);

        profileviewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
    private void initialize(View root)
    {
         name = (TextView)root.findViewById(R.id.name);
         auth = FirebaseAuth.getInstance();
         open=root.findViewById(R.id.open);
         rp=root.findViewById(R.id.rp);
         rc=root.findViewById(R.id.rc);
         set=root.findViewById(R.id.set);
         learn=root.findViewById(R.id.learn);
         open.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 ((Dashboard)getActivity()).open_side_drawer();
             }
         });
         rc.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(getActivity(), Referral.class));
             }
         });
         rp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(getActivity(), Redeem.class));
             }
         });
         set.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(getActivity(), Settings.class));
             }
         });
         learn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(getActivity(), Learning.class));
             }
         });
    }


}