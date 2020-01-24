package com.asc.home.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.asc.home.R;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    //Views declaration
    private TextView creditValue;
    private ImageView hamIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        creditValue = (TextView) findViewById(R.id.credit_value);
        creditValue.setOnClickListener(this);
        hamIcon = (ImageView) findViewById(R.id.ham_icon);
        hamIcon.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.ham_icon:
                startActivity(new Intent(Profile.this, Main.class));
                break;
            case R.id.credit_value:
                startActivity(new Intent(Profile.this, Wallet.class));
                break;
        }
    }
}
