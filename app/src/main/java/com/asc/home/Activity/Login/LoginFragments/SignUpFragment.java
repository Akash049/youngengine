package com.asc.home.Activity.Login.LoginFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.asc.home.Activity.Home.Dashboard;
import com.asc.home.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {
    Button signUp;
    ProgressBar progressBar;
    EditText email, password, confirmPassword;
    private FirebaseAuth auth;

    public SignUpFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_sign_up_, container, false);
        initialize(root);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), Dashboard.class));
                String emailVal = email.getText().toString();
                String passwordVal = password.getText().toString();
                String confirmPass = confirmPassword.getText().toString();
                if(!isValid(emailVal)){
                    Toast.makeText(getContext(),"Invalid email",Toast.LENGTH_SHORT).show();
                }else if(!passwordVal.equals(confirmPass)){
                    Toast.makeText(getContext(),"Password and confirm password do not match!!",Toast.LENGTH_SHORT).show();
                }else if(!TextUtils.isEmpty(emailVal) && !TextUtils.isEmpty(passwordVal) && !TextUtils.isEmpty(confirmPass)){
                    progressBar.setVisibility(View.VISIBLE);
                    signUp.setVisibility(View.GONE);
                    auth.createUserWithEmailAndPassword(emailVal,passwordVal).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressBar.setVisibility(View.GONE);
                                signUp.setVisibility(View.GONE);

                                Toast.makeText(getContext(),"Successfully registered",Toast.LENGTH_SHORT).show();
                                startActivity(
                                        new Intent(getContext(), Dashboard.class)
                                );
                            }else{
                                //Error
                                progressBar.setVisibility(View.GONE);
                                signUp.setVisibility(View.VISIBLE);
                                String error = task.getException().getMessage();
                                Toast.makeText(getContext(),"Error: " +email,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(getContext(),"Incomplete fields",Toast.LENGTH_LONG).show();
                }

            }
        });
       return root;
    }

    public void initialize(View root){
        signUp=root.findViewById(R.id.signup);
        email=root.findViewById(R.id.email);
        password=root.findViewById(R.id.password);
        confirmPassword=root.findViewById(R.id.confirm_password);
        progressBar = root.findViewById(R.id.progress_bar);
        auth = FirebaseAuth.getInstance();
    }

    public boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }


}
