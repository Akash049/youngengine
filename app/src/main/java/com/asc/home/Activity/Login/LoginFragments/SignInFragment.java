package com.asc.home.Activity.Login.LoginFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.asc.home.Activity.Home.Dashboard;
import com.asc.home.Activity.Login.OTPLogin;
import com.asc.home.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;


public class SignInFragment extends Fragment implements View.OnClickListener {

    private ProgressBar progressBar;
    TextView forgotpass;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN=0;
    Button button1;
    EditText emailEntry, passwordEntry;

    private FirebaseAuth auth;

    public SignInFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_sign__in_, container, false);
        initialize(root);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OTPLogin.class));
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                String email = emailEntry.getText().toString();
                String password = passwordEntry.getText().toString();
                if(!isValid(email)){
                    Toast.makeText(getContext(),"Invalid Email",Toast.LENGTH_SHORT).show();
                }else if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //Logged in Successfully
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getContext(),"Logged in successfully",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getContext(), Dashboard.class));
                            }else{
                                //Error
                                progressBar.setVisibility(View.GONE);
                                button1.setVisibility(View.VISIBLE);
                                String error = task.getException().getMessage();
                                Toast.makeText(getContext(),"Error: " +error,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(getContext(),"Please provide all details",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

    public void initialize(View root){
        emailEntry=root.findViewById(R.id.email_entry);
        passwordEntry=root.findViewById(R.id.password_entry);
        forgotpass=root.findViewById(R.id.forgot_pass);
        button1=root.findViewById(R.id.button1);
        progressBar = root.findViewById(R.id.progress_bar);
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                signIn();
                break;
        }
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

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent intent =new Intent(getActivity(), Dashboard.class);
            startActivity(intent);
        } catch (ApiException e) {
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }
}
