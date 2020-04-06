package com.asc.home.Activity.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.asc.home.Activity.Home.Dashboard;
import com.asc.home.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        auth = FirebaseAuth.getInstance();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        FirebaseUser user = auth.getCurrentUser();
                        if(user != null){
                            startActivity(new Intent(Splash.this, Dashboard.class));
                            finish();
                        }else if(user == null){
                            startActivity(new Intent(Splash.this, IntroductionScreens.class));
                            finish();
                        }

                    }
                }, 500);
    }
}
