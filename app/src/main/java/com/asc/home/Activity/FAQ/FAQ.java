package com.asc.home.Activity.FAQ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.asc.home.Activity.Main.Main;
import com.asc.home.Activity.Wallet.Wallet;
import com.asc.home.R;

public class FAQ extends AppCompatActivity  {
    private TextView creditValue;
    private ImageView hamIcon;
    private ImageView coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        //creditValue = (TextView) findViewById(R.id.credit_value);
        // creditValue.setOnClickListener(this);
        // hamIcon = (ImageView) findViewById(R.id.ham_icon);
        // hamIcon.setOnClickListener(this);
        //coin=(ImageView)findViewById(R.id.coin_icon);
        //coin.setOnClickListener(this);

    }

   /* @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ham_icon:
                startActivity(new Intent(FAQ.this, Main.class));
                break;
            case R.id.credit_value:
                startActivity(new Intent(FAQ.this, Wallet.class));
                break;
            case R.id.coin_icon:
                startActivity(new Intent(FAQ.this, Wallet.class));
                break;
        }
    }*/
}
