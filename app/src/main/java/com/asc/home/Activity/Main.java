package com.asc.home.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.asc.home.Adapter.DrawerListItemAdapter;
import com.asc.home.Model.NavDataModel;
import com.asc.home.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class Main extends AppCompatActivity implements View.OnClickListener {

    //Container Variables
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private NavDataModel navDataModel;
    private ActionBarDrawerToggle mDrawerToggle;

    //Widgets
    private ImageView hamIcon;
    private TextView creditValue;

    //Temp variable not in use
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_Active, R.id.navigation_Learnings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public void initialize(){
        creditValue = (TextView)findViewById(R.id.credit_value);
        creditValue.setOnClickListener(this);
        hamIcon = (ImageView) findViewById(R.id.ham_icon);
        hamIcon.setOnClickListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        NavDataModel[] drawerItem = new NavDataModel[4];

        //First set the header details for the view
        navDataModel = new NavDataModel();
        navDataModel.setUserName("Akash Chandra");

        //Setting up the list
        drawerItem[0] = navDataModel;
        drawerItem[1] = new NavDataModel(R.drawable.ic_user, "Profile");
        drawerItem[2] = new NavDataModel(R.drawable.ic_user_group, "Social");
        drawerItem[3] = new NavDataModel(R.drawable.ic_help_black_24dp, "FAQ");

        DrawerListItemAdapter drawerItemCustomAdapter = new DrawerListItemAdapter(this, R.layout.list_drawer_item_layout, drawerItem);
        mDrawerList.setAdapter(drawerItemCustomAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();
    }

    void setupDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    private void selectItem(int position) {

        switch (position) {
            case 0:
                break;
            case 1:
                mDrawerLayout.closeDrawers();
                startActivity(new Intent(Main.this, Profile.class));
                break;
            case 2:
                mDrawerLayout.closeDrawers();
                startActivity(new Intent(Main.this, Social.class));
                break;
            case 3:
                mDrawerLayout.closeDrawers();
                startActivity(new Intent(Main.this, Profile.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.ham_icon:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.credit_value:
                startActivity(new Intent(Main.this, Wallet.class));
                break;
        }
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }
}
