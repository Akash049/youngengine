package com.asc.home.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.asc.home.Activity.Main.Main;
import com.asc.home.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OTP_Login extends AppCompatActivity {
EditText ph_num,otp;
Button verify,send;
String url, url2;
RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p__login);
        ph_num=findViewById(R.id.ph_num);
        otp=findViewById(R.id.otp);
        verify=findViewById(R.id.verify_otp);
        send=findViewById(R.id.send_otp);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOTP();
            }
        });
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              verifyOTP();
            }
        });
        send=findViewById(R.id.send_otp);
        url = "http://3.88.131.77:8080/user/send_otp/";
        url2 = " http://3.88.131.77:8080/user/verify_otp/";
    }

    private void getOTP() {
        try {
            requestQueue = Volley.newRequestQueue(this);
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("Response", response);
                    try {
                        JSONObject responseObject = new JSONObject(response);
                        String status = responseObject.optString("status");
                        if (status.equals("1")) {
                            //otp_process.setText("OTP sent.");
                            Toast.makeText(getApplicationContext(), "OTP sent", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Please Insert Your Mobile Number", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "Could not send the OTP please try again.", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // As of f605da3 the following should work
                            NetworkResponse response = error.networkResponse;
                            Log.d("Response error", error.toString());
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> loginParams = new HashMap<String, String>();
                    loginParams.put("mobile", ph_num.getText().toString());
                    return loginParams;
                }
            };
            requestQueue.add(postRequest);
        } catch (Exception e) {

        }
    }
    private void verifyOTP() {
        try {
            requestQueue = Volley.newRequestQueue(this);
            StringRequest getRequest = new StringRequest(Request.Method.POST,url2, new Response.Listener<String>() {
                @Override

                public void onResponse(String response) {
                    Log.d("Response", response);
                    try {
                        JSONObject responseObject = new JSONObject(response);
                        String status = responseObject.optString("status");
                        if (status.equals("1")) {
                            //otp_process.setText("OTP sent.");
                            startActivity(new Intent(OTP_Login.this, Main.class));
                            Toast.makeText(getApplicationContext(), "OTP verified", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect OTP", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "Could not send the OTP please try again.", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }

            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // As of f605da3 the following should work
                            NetworkResponse response = error.networkResponse;
                            Log.d("Response error", error.toString());
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> loginParams = new HashMap<String, String>();
                    loginParams.put("mobile", ph_num.getText().toString());
                    loginParams.put("otp", otp.getText().toString());
                    return loginParams;
                }
            };
            requestQueue.add(getRequest);
        } catch (Exception e) {

        }
    }
}
