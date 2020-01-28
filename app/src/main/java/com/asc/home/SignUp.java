package com.asc.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.asc.home.Activity.Main.Main;

public class SignUp extends AppCompatActivity {
    TextView resignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        resignin=findViewById(R.id.resignin);
        resignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello=new Intent(SignUp.this,Login_Page.class);
                startActivity(hello);
            }
        });
    }

    public void buclick(View view) {
        Intent intent=new Intent(SignUp.this, Main.class);
        startActivity(intent);
    }
}
