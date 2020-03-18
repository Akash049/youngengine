package com.asc.home.Activity.Splash_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.asc.home.Activity.Starting.Starting;
import com.asc.home.R;

public class Splash extends AppCompatActivity {
private int slp=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
       // getSupportActionBar().hide();
        launcher launcher=new launcher();
        launcher.start();
    }
    private class launcher extends Thread{
        public void run()
        {
            try {
                sleep(100*slp);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            Intent intent = new Intent(Splash.this, Starting.class);
            startActivity(intent);
            Splash.this.finish();

        }

    }
}
