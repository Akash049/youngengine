package com.asc.home.Activity.LoginViewPager_Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.asc.home.Activity.Login.Login_Page;
import com.asc.home.Activity.Main.Main;
import com.asc.home.Activity.OTP_Login;
import com.asc.home.R;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class Sign_In_Fragment extends Fragment implements View.OnClickListener {
    Button button;
    EditText email;
    LoginButton loginButton;
    CallbackManager callbackManager;
    EditText password;
    TextView user,signin,signup,forgotpass;
    LinearLayout signinblue,signupblue,button2;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN=0;
    Button button1;
    EditText emailEntry, passwordEntry;

    public Sign_In_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_sign__in_, container, false);
        emailEntry=root.findViewById(R.id.email_entry);
        passwordEntry=root.findViewById(R.id.password_entry);
        forgotpass=root.findViewById(R.id.forgot_pass);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OTP_Login.class));
            }
        });
       // signin=root.findViewById(R.id.signin);
       // signup=findViewById(R.id.signup);
       // signinblue=findViewById(R.id.signinblue);
       // signupblue=findViewById(R.id.signupblue);
        button1=root.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id) {
                    case R.id.button1:
                        if (emailEntry.getText().toString().equals("akash@wiztute.com") &&
                                passwordEntry.getText().toString().equals("qwerty")
                        ) {
                            Toast.makeText(getApplicationContext(), "You have successfully logged in", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), Main.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getApplicationContext(), gso);

        root.findViewById(R.id.button2).setOnClickListener(this);

        return root;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                signIn();
                break;
            // ...
        }
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent intent =new Intent(getActivity(),Main.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }
}
