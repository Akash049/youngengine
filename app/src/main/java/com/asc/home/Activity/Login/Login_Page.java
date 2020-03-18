package com.asc.home.Activity.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.asc.home.R;
import com.asc.home.Activity.LoginViewPager_Fragments.Sign_In_Fragment;
import com.asc.home.Activity.LoginViewPager_Fragments.Sign_up_Fragment;
import com.asc.home.Adapter.ViewPagerAdapter;
import com.facebook.FacebookSdk;
import com.google.android.material.tabs.TabLayout;

public class Login_Page extends AppCompatActivity  {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login__page);
        toolbar=findViewById(R.id.my_toolbar);
        tabLayout=findViewById(R.id.my_tab_layout);
        viewPager=findViewById(R.id.my_view_pager);

        setSupportActionBar(toolbar);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

      /*  user=findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_Page.this, SignUp.class);
                startActivity(intent);
            }
        });
        button=findViewById(R.id.button2);
     //   email=findViewById(R.id.email);
        password=findViewById(R.id.password);*/
     /* loginButton= (LoginButton) findViewById(R.id.login_fb);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Intent intent = new Intent(Login_Page.this,Main.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
            }
        });
        callbackManager=CallbackManager.Factory.create();
        /*loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
           @Override
           public void onSuccess(LoginResult loginResult) {
                 Intent intent = new Intent(Login_Page.this,Main.class);
                 startActivity(intent);
           }

           @Override
           public void onCancel() {

           }

           @Override
           public void onError(FacebookException error) {

           }
       });*/
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Sign_In_Fragment(),"Sign  In");
        viewPagerAdapter.addFragment(new Sign_up_Fragment(),"Sign  Up");
        viewPager.setAdapter(viewPagerAdapter);
    }



/*
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button2:
                signIn();
                break;
            // ...
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent intent =new Intent(Login_Page.this,Main.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }*/
}
