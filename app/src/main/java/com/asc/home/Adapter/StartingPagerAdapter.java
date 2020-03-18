package com.asc.home.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class StartingPagerAdapter extends FragmentPagerAdapter {
    public final ArrayList<Fragment>fragmentArrayList=new ArrayList<>();

    public StartingPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
    public void addfragment(Fragment Fragment)
    {
        fragmentArrayList.add(Fragment);
    }


}
