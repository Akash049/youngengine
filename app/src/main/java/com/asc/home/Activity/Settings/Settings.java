package com.asc.home.Activity.Settings;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.UriMatcher;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.asc.home.Activity.Main.Main;
import com.asc.home.R;

public class Settings extends AppCompatActivity {
private static final int GALLERY_REQUEST_CODE=123;

ImageView profile,return_back;
Button pick,update;
ImageView info_edit,name_edit;
EditText info,nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
       update=findViewById(R.id.update);
        profile=findViewById(R.id.profile);
        pick=findViewById(R.id.pick);
        info=findViewById(R.id.info);
        nickname=findViewById(R.id.nickname);
        info_edit=findViewById(R.id.info_edit);
        name_edit=findViewById(R.id.name_edit);
        return_back=findViewById(R.id.return_back);
        return_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this,Main.class));
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Settings.this, Main.class));
                Toast.makeText(getApplicationContext(),"Profile Updated",Toast.LENGTH_LONG).show();
            }
        });
        info_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText("");
            }
        });

        name_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickname.setText("");
            }
        });
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Pick an Image"),GALLERY_REQUEST_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri imagedata = data.getData();
            profile.setImageURI(imagedata);
        }
    }
}
