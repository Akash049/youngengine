package com.asc.home.Activity.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.asc.home.R;
import com.asc.home.Activity.Login.LoginFragments.SignInFragment;
import com.asc.home.Activity.Login.LoginFragments.SignUpFragment;
import com.asc.home.Adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class LoginScreen extends AppCompatActivity  {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login__page);
        toolbar=findViewById(R.id.my_toolbar);
        tabLayout=findViewById(R.id.my_tab_layout);
        viewPager=findViewById(R.id.my_view_pager);

        setSupportActionBar(toolbar);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new SignInFragment(),"Sign  In");
        viewPagerAdapter.addFragment(new SignUpFragment(),"Sign  Up");
        viewPager.setAdapter(viewPagerAdapter);
    }
}
