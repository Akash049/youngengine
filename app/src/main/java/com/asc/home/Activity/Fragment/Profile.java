package com.asc.home.Activity.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asc.home.Activity.Main.Main;
import com.asc.home.Activity.ViewModels.ProfileviewModel;
import com.asc.home.Activity.Wallet.Wallet;
import com.asc.home.R;

public class Profile extends Fragment {

    LinearLayout rp,set,rc,learn;
    ImageView open;

    private ProfileviewModel profileviewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileviewModel =
                ViewModelProviders.of(this).get(ProfileviewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        initialize(root);
        profileviewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
    private void initialize(View root)
    {
         open=root.findViewById(R.id.open);
         rp=root.findViewById(R.id.rp);
         rc=root.findViewById(R.id.rc);
         set=root.findViewById(R.id.set);
         learn=root.findViewById(R.id.learn);
         open.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 ((Main)getActivity()).open_side_drawer();
             }
         });
         rc.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
           //      startActivity(new Intent(getActivity(),Wallet.class));
             }
         });
         rp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //startActivity(new Intent(getActivity(),Wallet.class));
             }
         });
         set.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                // startActivity(new Intent(getActivity(),Wallet.class));
             }
         });
         learn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                // startActivity(new Intent(getActivity(),Wallet.class));
             }
         });
    }


}