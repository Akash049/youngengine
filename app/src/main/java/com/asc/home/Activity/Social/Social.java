package com.asc.home.Activity.Social;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.asc.home.Activity.Main.Main;
import com.asc.home.Activity.Wallet.Wallet;
import com.asc.home.Adapter.EventListAdapter;
import com.asc.home.Model.EventModel;
import com.asc.home.R;

import java.util.ArrayList;

public class Social extends AppCompatActivity implements View.OnClickListener {
    RecyclerView mrecyclerView;
    EventListAdapter eventListAdapter;
    Button sharing;
    TextView referral_code;
    private TextView creditValue;
    private ImageView hamIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        creditValue = (TextView) findViewById(R.id.credit_value);
        creditValue.setOnClickListener(this);
        hamIcon = (ImageView) findViewById(R.id.ham_icon);
        hamIcon.setOnClickListener(this);
        mrecyclerView = findViewById(R.id.recyclerView10);          //Initialisation of the recycler view
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventListAdapter = new EventListAdapter(this, getMyList());
        mrecyclerView.setAdapter(eventListAdapter);
        sharing = findViewById(R.id.sharing);                      //Initialisation of the sharing button
        referral_code = findViewById(R.id.code);
        sharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = referral_code.getText().toString();               // passing referral code to string text
                Intent sharingintent = new Intent(Intent.ACTION_SEND);         //intent creation
                sharingintent.setType("text/plain");
                startActivity(Intent.createChooser(sharingintent, "Share Code Via"));//sending code by starting activity
            }
        });
    }

    private ArrayList<EventModel> getMyList() {
        ArrayList<EventModel> eventModels = new ArrayList<>();
        EventModel m = new EventModel();
        m.setTitle("Python");
        m.setCompanyname("wiztute");
        m.setStatus("ONGOING");
        m.setDate("15 jan 2020");
        m.setImg(R.drawable.images);
        eventModels.add(m);

        m = new EventModel();
        m.setTitle("Open a product and buy that product");
        m.setCompanyname("wiztute");
        m.setStatus("ONGOING");
        m.setDate("02 jan 2020");
        m.setImg(R.drawable.images);
        eventModels.add(m);

        m = new EventModel();
        m.setTitle("Data science");
        m.setCompanyname("wiztute");
        m.setStatus("COMPLETED");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.images);
        eventModels.add(m);

        m = new EventModel();
        m.setTitle("Blockchain");
        m.setCompanyname("Wiztute");
        m.setStatus("COMPLETED");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.images);
        eventModels.add(m);

        m = new EventModel();
        m.setTitle("Data science");
        m.setCompanyname("wiztute");
        m.setStatus("COMPLETED");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.images);
        eventModels.add(m);

        m = new EventModel();
        m.setTitle("Data science");
        m.setCompanyname("wiztute");
        m.setStatus("COMPLETED");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.images);
        eventModels.add(m);

        m = new EventModel();
        m.setTitle("Data science");
        m.setCompanyname("wiztute");
        m.setStatus("COMPLETED");
        m.setDate("10 jan 2020");
        m.setImg(R.drawable.images);
        eventModels.add(m);
        return eventModels;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ham_icon:
                startActivity(new Intent(Social.this, Main.class));
                break;
            case R.id.credit_value:
                startActivity(new Intent(Social.this, Wallet.class));
                break;
        }
    }
}