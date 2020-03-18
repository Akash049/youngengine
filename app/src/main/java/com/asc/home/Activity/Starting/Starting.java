package com.asc.home.Activity.Starting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.asc.home.Adapter.StartingPagerAdapter;
import com.asc.home.Activity.StartingViewPager_Fragments.Earn;
import com.asc.home.Activity.StartingViewPager_Fragments.Learn;
import com.asc.home.R;
import com.asc.home.Activity.StartingViewPager_Fragments.Work;

public class Starting extends AppCompatActivity {
     private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        viewPager=findViewById(R.id.starting_view_pager);
        setViewPager(viewPager);
    }

    public void setViewPager(ViewPager viewPager)
    {
        StartingPagerAdapter startingPagerAdapter=new StartingPagerAdapter(getSupportFragmentManager());
        startingPagerAdapter.addfragment(new Learn());
        startingPagerAdapter.addfragment(new Work());
        startingPagerAdapter.addfragment(new Earn());
        viewPager.setAdapter(startingPagerAdapter);
    }
    public void learn_change_page()
    {
        viewPager.setCurrentItem(1,true);
    }
    public void work_change_page()
    {
        viewPager.setCurrentItem(2,true);
    }


}
