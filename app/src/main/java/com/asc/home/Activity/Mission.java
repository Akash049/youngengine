package com.asc.home.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.asc.home.R;

public class AtclickActivity extends AppCompatActivity {
    TextView textView;
    String [] spinnerlist={"india","australia","england","pakistan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atclick);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,spinnerlist);
        ActionBar actionBar=getSupportActionBar();
        Spinner counspinner=(Spinner)findViewById(R.id.description);
        counspinner.setAdapter(arrayAdapter);
    }
}
