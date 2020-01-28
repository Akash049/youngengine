package com.asc.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asc.home.Activity.Main.Main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class Login_Page extends AppCompatActivity {
    Button button;
    EditText email;
    LoginButton loginButton;
    CallbackManager callbackManager;
EditText password;
TextView user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login__page);
        user=findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_Page.this,SignUp.class);
                startActivity(intent);
            }
        });
        button=findViewById(R.id.button2);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
       loginButton=findViewById(R.id.fb_login);
       callbackManager=CallbackManager.Factory.create();
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

    public void buclick(View view) {
        String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                "\\@" +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                "(" +

                "\\." +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                ")+";

String mail=email.getText().toString();
        Matcher matcher= Pattern.compile(validemail).matcher(mail);
        if(matcher.matches())
        {
            Toast.makeText(getApplicationContext(),"true",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(Login_Page.this,Main.class);
            startActivity(intent);
        }

        else{
            Toast.makeText(getApplicationContext(),"Enter valid email",Toast.LENGTH_LONG).show();
        }if (password.getText().toString().equals(" ")) {
            password.setError("Enter password");
        }
    }
}
