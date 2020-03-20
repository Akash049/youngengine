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
import com.asc.home.Adapter.NewListAdapter;
import com.asc.home.Model.EventModel;
import com.asc.home.Model.NewModel;
import com.asc.home.R;

import java.util.ArrayList;

public class Social extends AppCompatActivity{
    RecyclerView mrecyclerView;
    NewListAdapter eventListAdapter;
    Button sharing;
    TextView referral_code;
    private TextView creditValue;
    private ImageView hamIcon;
    private ImageView coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
//        creditValue = (TextView) findViewById(R.id.credit_value);
  //      creditValue.setOnClickListener(this);
    //    hamIcon = (ImageView) findViewById(R.id.ham_icon);
      //  hamIcon.setOnClickListener(this);
     //   coin = (ImageView) findViewById(R.id.coin_icon);
    //    coin.setOnClickListener(this);
        mrecyclerView = findViewById(R.id.recyclerView10);          //Initialisation of the recycler view
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventListAdapter = new NewListAdapter(this, getMyList());
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

    private ArrayList<NewModel> getMyList() {
        ArrayList<NewModel> eventModels = new ArrayList<>();
        NewModel m = new NewModel();
        m.setNew_title("MUN In IITD");
        m.setNew_date("21st Feb, 2020");
        m.setTaskcoins("133");
        m.setTasks("5");
        m.setNew_image(R.drawable.people);
        eventModels.add(m);

        m=new NewModel();
        m.setNew_title("College Fest In Gargi");
        m.setNew_date("22nd Feb, 2020");
        m.setTaskcoins("133");
        m.setTasks("5");
        m.setNew_image(R.drawable.people2);
        eventModels.add(m);

        m=new NewModel();
        m.setNew_title("Cultural Fest In S");
        m.setNew_date("23rd Feb, 2020");
        m.setTaskcoins("133");
        m.setTasks("5");
        m.setNew_image(R.drawable.people3);
        eventModels.add(m);
        return eventModels;
    }

   // @Override
   /* public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ham_icon:
                startActivity(new Intent(Social.this, Main.class));
                break;
            case R.id.credit_value:
                startActivity(new Intent(Social.this, Wallet.class));
                break;
            case R.id.coin_icon:
                startActivity(new Intent(Social.this, Wallet.class));
                break;
        }
    }*/
}